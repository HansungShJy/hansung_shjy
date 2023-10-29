import React, { useState } from "react";
import axios from "axios";
import "./Signup.css";
import signuplogo from "../assets/signuplogo.png";

function Signup() {
  const [userId, setuserId] = useState("");
  const [userPW, setuserPW] = useState("");
  const [CheckPW, setCheckPW] = useState("");
  const [userName, setuserName] = useState("");
  const [userNickName, setuserNickName] = useState("");
  const [userBirth, setuserBirth] = useState(Date);
  const [userEmail, setuserEmail] = useState("");

  const [usableId, setUsableId] = useState(false);
  const [usableEmail, setusableEmail] = useState("");

  const onIdHandler = (e) => {
    setuserId(e.target.value);
  };

  const onPWHandler = (e) => {
    setuserPW(e.target.value);
  };

  const onCheckPWHandler = (e) => {
    setCheckPW(e.target.value);
  };

  const onNameHandler = (e) => {
    setuserName(e.target.value);
  };

  const onNickNameHandler = (e) => {
    setuserNickName(e.target.value);
  };

  const onBirthHandler = (e) => {
    setuserBirth(e.target.value);
  };

  const onEmailHandler = (e) => {
    setuserEmail(e.target.value);
  };

  const EmailConfirm = () => {
    // 모르겠다
  };

  const idValidation = () => {
    axios
      .post("http://localhost:3000/verify/id", {
        id: userId,
      })
      .then((response) => {
        console.log(response);
        if (response.data === false) {
          alert("사용 가능한 아이디입니다.");
          setUsableId(true);
        } else {
          alert("이미 사용중인 아이디입니다.");
          setuserId("");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  function SignUpContinue() {
    const DoubleCheckPW =
      /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{10,}$/;
    if (userPW !== CheckPW) {
      alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요!");
    } else if (!DoubleCheckPW.test(userPW)) {
      alert(
        "대소문자, 숫자, 특수문자 포함 10자 이상입니다. 다시 입력해주세요."
      );
    } else if (
      userId === "" ||
      userPW === "" ||
      userName === "" ||
      userNickName === "" ||
      userBirth === "" ||
      userEmail === ""
    ) {
      alert("빈칸을 모두 채워주세요!");
    } else {
      axios
        .post(`http://localhost:3000/signup`, {
          id: userId,
          pw: userPW,
          name: userName,
          nickname: userNickName,
          birth: userBirth,
          email: userEmail,
        })
        .then((response) => {
          console.log(response);
          alert("회원가입 완료!");
          document.location.href = "./";
        })
        .catch((error) => {
          console.log(error);
          alert("실패했습니다.");
        });
    }
  }

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
          userName="userId"
          value={userId}
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
          onChange={onIdHandler}
        />
        <button
          type="button"
          onClick={idValidation}
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
          style={{
            fontSize: "17px",
            fontWeight: "bold",
            marginLeft: "450px",
            marginTop: "26px",
          }}
          className="pw-form"
          for="password"
        >
          비밀번호
        </label>
        <input
          type="password"
          value={userPW}
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
            marginLeft: "552px",
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
          userName="userName"
          value={userName}
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
          name="nickName"
          value={userNickName}
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
          userName="userBirth"
          value={userBirth}
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
          type="Email"
          name="Email"
          value={userEmail}
          placeholder="  ex) Email@gamil.com"
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
          onClick={usableEmail}
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
          onClick={SignUpContinue}
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
