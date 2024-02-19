import React, { useState } from "react";
import homelogo from "../assets/homelogoset.png";
import axios from "axios";

function Findpw() {
  const [userId, setuserId] = useState("");
  const [userBirth, setuserBirth] = useState("");
  const [userEmail, setuserEmail] = useState("");

  const onBirthHandler = (e) => {
    setuserBirth(e.target.value);
  };

  const onEmailHandler = (e) => {
    setuserEmail(e.target.value);
  };

  const onIdHandler = (e) => {
    setuserId(e.target.value);
  };
  const FindingPW = (e) => {
    axios
      .post("http://localhost:3000/findpw", {
        id: userId,
        email: userEmail,
        birth: userBirth,
      })
      .then((response) => {
        console.log(response);
        //alert("비밀번호는 " + response.data + " 입니다.");
        if (
          window.confirm(
            "비밀번호는 " +
              response.data +
              " 입니다." +
              "\n확인을 누르면 로그인창으로 돌아갑니다."
          ) === true
        ) {
          document.location.href = "./";
        } else {
          console.log("비밀번호 찾기 종료");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("비밀번호를 찾을 수 없습니다. 다시 시도해주세요!");
      });
  };
  return (
    <div>
      <div>
        <img
          src={homelogo}
          alt="homelogoset.png"
          width="380"
          style={{
            marginLeft: "480px",
            marginTop: "100px",
            marginBottom: "20px",
          }}
        />
        <br />
        <label
          className="find-pw"
          htmlFor="text"
          style={{
            fontSize: "23px",
            fontWeight: "bold",
            marginLeft: "630px",
          }}
        >
          비밀번호 찾기
        </label>
        <br />
        <label
          style={{
            fontSize: "17px",
            fontWeight: "bold",
            marginLeft: "497px",
          }}
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
            marginLeft: "13px",
            marginTop: "30px",
          }}
          onChange={onIdHandler}
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
            marginTop: "40px",
            marginLeft: "15px",
          }}
          onChange={onEmailHandler}
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
            marginTop: "40px",
            marginLeft: "15px",
          }}
          onChange={onBirthHandler}
        />
        <br />
        <button
          className="Findpw-button"
          type="button"
          onClick={FindingPW}
          formAction=""
          style={{
            fontSize: "15px",
            fontWeight: "bold",
            width: "280px",
            height: "30px",
            backgroundColor: "rgba(175, 205, 245, 0.4)",
            color: "000000",
            border: "0px",
            borderRadius: "10px",
            marginTop: "40px",
            marginLeft: "560px",
          }}
        >
          확인
        </button>
      </div>
    </div>
  );
}

export default Findpw;
