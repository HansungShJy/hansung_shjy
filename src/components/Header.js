import React, { useState } from "react";
import Nav from "./\bNav";
import "./Header.css";
import { Cookies } from "react-cookie";
import axios from "axios";
import homelogo from "../assets/homelogoset.png";

function Header() {
  const userId = useState("");
  const cookie = new Cookies();
  const encodedNickname = cookie.get("nickname");
  cookie.get("nickname", decodeURIComponent(encodedNickname));

  function onClickLogout() {
    {
      axios
        .get(`http://localhost:3000/logout`)
        .then((response) => {
          console.log(response.data);
          document.location.href = "/";
          //props.history.push("./");
        })
        .catch((error) => {
          console.log(error);
          alert("errrrror");
        });
    }
  }

  return (
    <div>
      <div className="header-logo">
        <img src={homelogo} alt="homelogoset.png" width="340" />
      </div>
      <div className="label_name">
        <label
          style={{
            fontSize: "15px",
            fontWeight: "bold",
            color: "000000",
          }}
        >
          {decodeURIComponent(encodedNickname)}
        </label>
        <label
          onClick={onClickLogout}
          style={{
            fontSize: "15px",
            fontWeight: "bold",
            color: "000000",
          }}
        >
          님 | 로그아웃
        </label>
      </div>
      <Nav />
    </div>
  );
}

export default Header;
