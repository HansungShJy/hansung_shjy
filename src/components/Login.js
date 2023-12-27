import React, { useState } from "react";
import "./Login.css";
import { Cookies } from "react-cookie";
import homelogo from "../assets/homelogoset.png";
import axios from "axios";

function Login() {
  const [userId, setuserId] = useState("");
  const [userPW, setuserPW] = useState("");

  const cookie = new Cookies();

  const onIDHandler = (e) => {
    setuserId(e.target.value);
  };
  const onPWHandler = (e) => {
    setuserPW(e.target.value);
  };

  function onClickLogin() {
    axios
      .post(`http://localhost:3000/login`, {
        id: userId,
        pw: userPW,
      })
      .then((response) => {
        console.log(response.data);
        alert("반갑습니다 회원님");
        cookie.remove("userId");
        cookie.set("id", userId);
        cookie.set("user_id", response.data.userID);
        cookie.set("couple_id", response.data.couple.coupleID);
        cookie.set("nickname", encodeURIComponent(response.data.nickname));

        document.location.href = "/diary";
      })
      .catch((error) => {
        console.log(error);
        if (error.message === 400) {
          alert("ID나 PW를 확인해주세요.");
        }
      });
  }

  function onClickSignup() {
    document.location.href = "/joinservice";
  }

  function onClickFindID() {
    document.location.href = "/findid";
  }

  function onClickFindPW() {
    document.location.href = "/findpw";
  }

  return (
    <div>
      <div className="home_logo">
        <img src={homelogo} alt="homelogoset.png" width="330" />
      </div>
      <div className="login-container">
        <div>
          <label className="id-form" htmlFor="id" style={{ fontSize: "15px" }}>
            아이디
          </label>

          <input
            type="text"
            name="id"
            value={userId}
            style={{
              width: "290px",
              height: "40px",
              fontSize: "15px",
              left: "200px",
              backgroundColor: "rgba(202, 225, 245, 0.3)",
              borderRadius: "8px",
              border: "none",
              marginTop: "20px",
            }}
            onChange={onIDHandler}
          />
        </div>
        <div>
          <label
            className="pw-form"
            htmlFor="password"
            style={{ fontSize: "15px" }}
          >
            비밀번호
          </label>
          <input
            className="Login-pw-Form"
            type="password"
            value={userPW}
            style={{
              width: "290px",
              height: "40px",
              fontSize: "15px",
              left: "200px",
              backgroundColor: "rgba(202, 225, 245, 0.3)",
              borderRadius: "8px",
              border: "none",
              marginTop: "15px",
            }}
            onChange={onPWHandler}
          />
        </div>
        <br />
        <div>
          <button
            className="Login-submit-button"
            type="button"
            onClick={onClickLogin}
            formAction=""
            style={{
              fontSize: "15px",
              fontWeight: "bold",
              width: "290px",
              height: "30px",
              backgroundColor: "rgba(175, 205, 245, 0.4)",
              color: "000000",
              border: "0px",
              borderRadius: "10px",
              marginBottom: "11px",
            }}
          >
            로그인
          </button>
        </div>
        <label
          onClick={onClickFindID}
          style={{
            fontSize: "13px",
            fontWeight: "bold",
            color: "000000",
            marginLeft: "37px",
          }}
        >
          아이디 찾기
        </label>
        <label
          style={{
            fontSize: "8px",
            color: "#d9d9d9",
            marginLeft: "8px",
            marginRight: "8px",
          }}
        >
          |
        </label>
        <label
          onClick={onClickFindPW}
          style={{ fontSize: "13px", fontWeight: "bold", color: "000000" }}
        >
          비밀번호 찾기
        </label>
        <label
          style={{
            fontSize: "7px",
            color: "#d9d9d9",
            marginLeft: "8px",
            marginRight: "8px",
          }}
        >
          |
        </label>
        <label
          onClick={onClickSignup}
          style={{ fontSize: "13px", fontWeight: "bold", color: "000000" }}
        >
          회원가입
        </label>
      </div>
    </div>
  );
}

export default Login;
