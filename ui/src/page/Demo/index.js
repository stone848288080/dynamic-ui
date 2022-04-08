import React, { Component } from "react";
import { ReactDOM } from "react";
import {Button,Box,TextField,Stack} from "@mui/material";
import axios from "axios";

export default class Demo extends Component {
  state = {text:"Hello World",websocketMsg:"web socket message"}
  constructor(props){
      super(props);
      this.webSocket = null;
      if('WebSocket' in window){
        console.log("浏览器支持websocket，继续websocket工作");
        this.webSocket = new WebSocket('ws://localhost:8888/websocket');
      }else{
        alert("该浏览器不支持websocket");
      }
  }
    
  getText = () => {
      let _this = this;
      axios.get('/demo/get-default-text').then(
        (response)=>{
            console.log(response);
            _this.setState((prevState,props)=>({
                text: prevState.text+"\n"+response.data
            }))
        }
      )
      .catch((error)=>{
          console.log(error);
      }
      );
  }


  triggerWebsocket= async ()=>{
    let _this = this;

    
    _this.webSocket.onopen=function(event){
      console.log("websocket 建立连接")
    }
    
    _this.webSocket.onclose = function(event){
      console.log("websocket 断开链接")
    }

    _this.webSocket.onmessage = function(event){
      console.log("收到websocket的消息："+ event.data);
      _this.appendWebSocketMsg(event.data);
    }

    _this.webSocket.onerror = function(event){
      console.log("web socket 通信发生错误");
    }

      let response =null;
      try{
        console.log("start trigger");
        response = await axios.get('/demo/get-websocket-text');
        
      }catch(e){
        console.error(e);
      }  
  }

  handleClick=()=>{
    console.log("handle click start");
    if(this.timer){
        console.log(this.timer)
        clearInterval(this.timer);
    }
    console.log("interval start");
    this.timer = setInterval(()=>{
        console.log("interval is doing...");
        this.getText();
    },1000)
  }

  appendWebSocketMsg = (message)=>{
      this.setState((prevState,props)=>({
          websocketMsg: prevState.websocketMsg + "\n" + message
      }))
  }

  componentDidMount(){
    window.onbeforeunload = this.onbeforeunload;
  }

  componentWillUnmount(){
    window.onbeforeunload = this.onbeforeunload;
  }

  onbeforeunload=()=>{
    this.webSocket.close();
  }

  render() {
    return (
      <div>
        <Box
          component="form"
          sx={{
            "& .MuiTextField-root": { m: 1, width: "60ch" },
          }}
          noValidate
          autoComplete="off"
        >
          <div>
            <TextField
              id="outlined-multiline-static"
              label="普通http消息窗格"
              multiline
              rows={8}
              value={this.state.text}
            />
          </div>
          <div>
            <TextField
              id="outlined-multiline-static"
              label="websocket消息窗格"
              multiline
              rows={8}
              value={this.state.websocketMsg}
            />
          </div>
        </Box>
        <Stack direction="row" spacing={2}>
          <Button variant="contained" onClick={this.handleClick}>Submit</Button>
          <Button variant="contained" onClick={this.triggerWebsocket}>WebSocket触发</Button>
        </Stack>
        
      </div>
    );
  }
}
