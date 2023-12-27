import React, { useState } from "react";
import { useCookies } from "react-cookie";
import homelogoset from "../assets/homelogoset.png";
import axios from "axios";

function InviteCouple() {
  const [CoupleEmail, setCoupleEmail] = useState("");
  const [confirmcode, setConfirmCode] = useState("");
  const [userDday, setuserDday] = useState(Date);
  const [emailConfirmResponse, setEmailConfirmResponse] = useState(null);
  const [cookies] = useCookies(["id"]);
  const userid = cookies.id;

  const InviteCoupleHandler = (e) => {
    setCoupleEmail(e.target.value);
  };

  const onCodeHandler = (e) => {
    setConfirmCode(e.target.value);
  };

  const onDdayHandler = (e) => {
    setuserDday(e.target.value);
  };

  const EmailConfirm = () => {
    //이메일 인증 요청을 누르면 code가 감
    axios
      .post(
        "http://localhost:3000/confirm/email/couple",
        {},
        {
          params: { email: CoupleEmail },
        }
      )

      .then((response) => {
        console.log(response);
        alert("인증 코드가 상대방의 이메일로 전송되었습니다.");
        setEmailConfirmResponse(response.data);
      })
      .catch((error) => {
        console.log("email error", error);
      });
  };

  const CodeConfirm = () => {
    const inputCode = document.getElementById("authCode").value; // 인증코드 입력값 가져오기
    console.log(emailConfirmResponse);
    if (inputCode === emailConfirmResponse) {
      alert("상대방의 이메일이 인증되었습니다.");
      console.log(userid);
    } else {
      alert("상대방의 이메일이 인증되지 않았습니다. 다시 시도해주세요.");
    }
  };

  const onClickConnect = () => {
    console.log(typeof userid);
    if (CoupleEmail === "" || userDday === "" || confirmcode === "") {
      alert("입력 하지 않은 칸이 있습니다.");
    } else {
      axios
        .post("http://localhost:3000/connect", {
          id: userid,
          otherid: CoupleEmail,
          dday: userDday,
        })
        .then((response) => {
          console.log(response);
          alert("상대방과 연결되었습니다.");
          document.location.href = "./";
        })
        .catch((error) => {
          console.log(error);
          alert("상대방과 연결 실패! 다시 시도해주세요.");
        });
    }
  };

  return (
    <div>
      <div className="logo-container">
        <img
          src={homelogoset}
          alt="homelogoset.png"
          width="300"
          style={{
            paddingTop: "10px",
            marginLeft: "540px",
            marginTop: "90px",
            marginBottom: "25px",
          }}
        />
      </div>
      <br />
      <div>
        <label
          style={{
            fontSize: "24px",
            marginLeft: "570px",
          }}
        >
          당신의 연인을 초대하세요.
        </label>
        <br />
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "452px" }}
        >
          연애 시작일
        </label>
        <input
          type="date"
          userName="userdday"
          value={userDday}
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "20px",
            marginLeft: "15px",
          }}
          onChange={onDdayHandler}
        />
        <br />
        <label
          style={{
            fontSize: "17px",
            fontWeight: "bold",
            marginLeft: "480px",
          }}
        >
          이메일
        </label>
        <input
          type="email"
          name="email"
          value={CoupleEmail}
          placeholder="     ex) email@gamil.com"
          style={{
            width: "280px",
            height: "40px",
            fontSize: "15px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "15px",
            marginLeft: "20px",
          }}
          onChange={InviteCoupleHandler}
        />
        <button
          type="button"
          id="emailAuth"
          onClick={EmailConfirm}
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
        <label
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "475px" }}
        >
          인증코드
        </label>
        <input
          type="code"
          name="code"
          id="authCode"
          value={confirmcode}
          placeholder="    인증코드를 입력해주세요."
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
          onChange={onCodeHandler}
        />
        <button
          type="button"
          onClick={CodeConfirm}
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
          확인
        </button>
        <br />
        <button
          type="button"
          onClick={onClickConnect}
          style={{
            fontSize: "17px",
            fontWeight: "bold",
            width: "160px",
            height: "33px",
            backgroundColor: "rgba(175, 205, 245, 0.4)",
            color: "000000",
            border: "0px",
            borderRadius: "4px",
            marginTop: "60px",
            marginLeft: "620px",
          }}
        >
          연결 하기
        </button>
      </div>
    </div>
  );
}

export default InviteCouple;
