import React, { Component } from "react";
import { ReactDOM } from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import axios from "axios";

export default class Demo extends Component {
  state = {text:"Hello World"}
    
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

  componentDidMount(){
    
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
              label="Multiline"
              multiline
              rows={8}
              value={this.state.text}
            />
          </div>
        </Box>
        <Button variant="contained" onClick={this.handleClick}>Submit</Button>
      </div>
    );
  }
}
