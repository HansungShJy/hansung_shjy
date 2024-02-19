import React, { useState } from "react";
import homelogo from "../assets/homelogoset.png";
import axios from "axios";

function Findid() {
  const [userBirth, setuserBirth] = useState("");
  const [userEmail, setuserEmail] = useState("");

  const onBirthHandler = (e) => {
    setuserBirth(e.target.value);
  };

  const onEmailHandler = (e) => {
    setuserEmail(e.target.value);
  };

  const FindingID = (e) => {
    axios
      .post("http://localhost:3000/findid", {
        email: userEmail,
        birth: userBirth,
      })
      .then((response) => {
        console.log(response);
        if (
          window.confirm(
            "아이디는 " +
              response.data +
              " 입니다" +
              "\n비밀번호도 찾고싶으신 분은 확인을 눌러주세요."
          ) === true
        ) {
          document.location.href = "./findpw";
        } else {
          console.log("아이디 찾기 종료");
        }
      })
      .catch((error) => {
        console.log(error);
        alert("아이디를 찾을 수 없습니다. 다시 시도해주세요!");
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
          className="find-id"
          htmlFor="id"
          style={{
            fontSize: "23px",
            fontWeight: "bold",
            marginLeft: "630px",
          }}
        >
          아이디 찾기
        </label>
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
            marginLeft: "17px",
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
            marginLeft: "17px",
          }}
          onChange={onBirthHandler}
        />
        <br />
        <button
          className="Findid-button"
          type="button"
          onClick={FindingID}
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
            marginLeft: "562px",
          }}
        >
          확인
        </button>
      </div>
    </div>
  );
}

export default Findid;
