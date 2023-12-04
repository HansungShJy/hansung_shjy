import Header from "./Header";
import React, { useState, useEffect } from "react";
import { useCookies } from "react-cookie";
import axios from "axios";
import "./Mypage.css";

function Mypage() {
  const [userId, setuserId] = useState("");
  const [userPW, setuserPW] = useState("");
  const [userNickName, setuserNickName] = useState("");
  const [userBirth, setuserBirth] = useState(Date);
  const [userEmail, setuserEmail] = useState("");

  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;

  const onIdHandler = (e) => {
    setuserId(e.target.value);
  };

  const onPWHandler = (e) => {
    setuserPW(e.target.value);
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

  const onSaveHandler = () => {
    axios
      .patch(
        "http://localhost:3000/mypage/edit",
        {
          userid: userid,
          nickname: userNickName,
          birth: userBirth,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        console.log("데이터 수정 성공:", res);
        alert("수정완료");
      })
      .catch((err) => {
        console.error("데이터 수정 실패:", err);
        alert("수정 실패");
      });
  };

  useEffect(() => {
    axios
      .get(`http://localhost:3000/mypage`, {
        params: {
          userid: userid,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "33333");
        const userData = res.data;
        setuserId(userData.id);
        setuserPW(userData.pw);
        setuserNickName(userData.nickname);
        setuserBirth(userData.birth);
        setuserEmail(userData.email);
      })

      .catch((err) => {
        console.log(err + "::err");
      });
  }, [userid]);

  return (
    <div>
      <Header />
      <br />
      <label className="type-lb1">닉네임</label>
      <input
        type="text"
        name="nickname"
        value={userNickName}
        onChange={onNickNameHandler}
      />
      <br />
      <label className="type-lb1">아이디</label>
      <input
        type="text"
        userName="userId"
        value={userId}
        disabled={true}
        onChange={onIdHandler}
      />
      <br />
      <label className="type-lb2">비밀번호</label>
      <input
        type="password"
        value={userPW}
        disabled={true}
        onChange={onPWHandler}
      />
      <br />
      <label className="type-lb2">생년월일</label>
      <input
        type="date"
        userName="userBirth"
        value={userBirth}
        onChange={onBirthHandler}
      />
      <br />
      <label className="type-lb1">이메일</label>
      <input
        type="Email"
        name="Email"
        id="email"
        value={userEmail}
        disabled={true}
        onChange={onEmailHandler}
      />
      <br />
      <button
        className="btn-edit"
        type="button"
        onClick={onSaveHandler}
        formAction=""
      >
        수정
      </button>
    </div>
  );
}

export default Mypage;
