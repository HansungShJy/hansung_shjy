import React, { useState } from "react";
import "./Signup.css";
import signuplogo from "../assets/signuplogo.png";

function Signup() {
  const [Id, setId] = useState("");
  const [Password, setPassword] = useState("");
  const [CheckPW, setCheckPW] = useState("");
  const [Name, setName] = useState("");
  const [NickName, setNickName] = useState("");
  const [Birth, setBirth] = useState(Date);
  const [Email, setEmail] = useState("");

  const [DoubleCheckId, setDoubleCheckId] = useState("");
  const [ConfirmEmail, setConfirmEmail] = useState("");

  const onIDHandler = (e) => {
    setId(e.target.value);
  };

  const onPWHandler = (e) => {
    setPassword(e.target.value);
  };

  const onSubmitHandler = (e) => {
    e.preventDefault();
  };

  const onCheckPWHandler = (e) => {
    setCheckPW(e.target.value);
  };

  const onNameHandler = (e) => {
    setName(e.target.value);
  };

  const onNickNameHandler = (e) => {
    setNickName(e.target.value);
  };

  const onBirthHandler = (e) => {
    setBirth(e.target.value);
  };

  const onEmailHandler = (e) => {
    setEmail(e.target.value);
  };

  return (
    <div>
      <div className="logo-container">
        <img
          src={signuplogo}
          alt="signuplogo.png"
          width="380"
          style={{
            paddingTop: "10px",
            marginLeft: "495px",
            marginBottom: "-15px",
          }}
        />
      </div>
      <div className="Signup-Form">
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "493px" }}
        >
          {" "}
          아이디{" "}
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
            marginLeft: "10px",
          }}
          onChange={onIDHandler}
        />
        <button
          type="button"
          onClick={DoubleCheckId}
          style={{
            fontSize: "11px",
            width: "70px",
            height: "25px",
            marginLeft: "10px",
            marginTop: "50px",
            backgroundColor: "rgba(175, 205, 245, 0.4)",
            color: "black",
            border: "0px",
            borderRadius: "5px",
            fontWeight: "bold",
          }}
        >
          중복 확인
        </button>
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "483px" }}
          className="pw-form"
          for="password"
        >
          비밀번호
        </label>
        <input
          type="password"
          value={Password}
          placeholder="   대소문자, 숫자, 특수문자 포함 10자 이상"
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "15px",
            marginLeft: "10px",
          }}
          onChange={onPWHandler}
        />
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "450px" }}
          for="password"
        >
          비밀번호 확인
        </label>
        <input
          type="password"
          value={CheckPW}
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "15px",
            marginRight: "100px",
            marginLeft: "10px",
          }}
          onChange={onCheckPWHandler}
        />
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "509px" }}
        >
          {" "}
          이름{" "}
        </label>
        <input
          type="text"
          name="name"
          value={Name}
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "20px",
            marginLeft: "10px",
          }}
          onChange={onNameHandler}
        />
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "495px" }}
        >
          {" "}
          닉네임{" "}
        </label>
        <input
          type="text"
          name="nickname"
          value={NickName}
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "20px",
            marginLeft: "10px",
          }}
          onChange={onNickNameHandler}
        />
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "485px" }}
        >
          생년월일
        </label>
        <input
          type="date"
          name="birth"
          value={Birth}
          placeholder="8자리로 입력해주세요 ex)19990101"
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "20px",
            marginLeft: "11px",
          }}
          onChange={onBirthHandler}
        />
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "500px" }}
        >
          이메일
        </label>
        <input
          type="email"
          name="email"
          value={Email}
          placeholder="  ex) email@gamil.com"
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "20px",
            marginLeft: "11px",
          }}
          onChange={onEmailHandler}
        />
        <button
          type="button"
          onClick={ConfirmEmail}
          style={{
            fontSize: "11px",
            width: "70px",
            height: "25px",
            marginLeft: "10px",
            marginTop: "30px",
            backgroundColor: "rgba(175, 205, 245, 0.4)",
            color: "black",
            border: "0px",
            borderRadius: "5px",
            fontWeight: "bold",
          }}
        >
          인증 요청
        </button>
        <br />
        <button
          type="button"
          onChange={onSubmitHandler}
          formAction=""
          style={{
            fontSize: "15px",
            fontWeight: "bold",
            width: "200px",
            height: "30px",
            backgroundColor: "rgba(175, 205, 245, 0.4)",
            color: "000000",
            border: "0px",
            borderRadius: "4px",
            marginTop: "30px",
            marginLeft: "600px",
          }}
        >
          다음
        </button>
      </div>
    </div>
  );
}

export default Signup;
