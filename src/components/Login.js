import React, { useState } from "react";
import "./Login.css";
import homelogo from "../assets/homelogoset.png";

function Login() {
  const [Id, setId] = useState("");
  const [Password, setPassword] = useState("");

  const onIDHandler = (e) => {
    setId(e.target.value);
  };
  const onPWHandler = (e) => {
    setPassword(e.target.value);
  };
  const onSubmitHandler = (e) => {
    e.preventDefault();

    console.log("ID", Id);
    console.log("Password", Password);

    // let body = {
    //   id: Id,
    //   password: Password,
    // };
  };

  return (
    <div className="login-container">
      <div>
        <img src={homelogo} alt="homelogoset.png" width="290" />
      </div>

      <div>
        <label className="id-form" htmlFor="id">
          아이디
        </label>

        <input
          type="text"
          name="id"
          value={Id}
          style={{
            width: "280px",
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
        <label className="pw-form" htmlFor="password">
          비밀번호
        </label>
        <input
          className="Login-pw-Form"
          type="password"
          value={Password}
          style={{
            width: "280px",
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
          onChange={onSubmitHandler}
          formAction=""
          style={{
            fontSize: "13px",
            fontWeight: "bold",
            width: "280px",
            height: "30px",
            backgroundColor: "rgba(175, 205, 245, 0.4)",
            color: "000000",
            border: "0px",
            borderRadius: "10px",
            marginBottom: "6px",
          }}
        >
          로그인
        </button>
      </div>
      <label
        style={{
          fontSize: "6px",
          fontWeight: "bold",
          color: "000000",
          marginLeft: "50px",
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
      <label style={{ fontSize: "6px", fontWeight: "bold", color: "000000" }}>
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
      <label style={{ fontSize: "6px", fontWeight: "bold", color: "000000" }}>
        회원가입
      </label>
    </div>
  );
}

export default Login;
