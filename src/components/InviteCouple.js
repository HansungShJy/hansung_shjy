import React, { useState } from "react";
import homelogoset from "../assets/homelogoset.png";

function InviteCouple() {
  const [CoupleEmail, setCoupleEmail] = useState("");

  const InviteCoupleHandler = (e) => {
    setCoupleEmail(e.target.value);
  };

  const onSubmitHandler = (e) => {
    e.preventDefault();
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
            marginTop: "100px",
            marginBottom: "30px",
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
          style={{ fontSize: "17px", fontWeight: "bold", marginLeft: "460px" }}
        >
          이메일
        </label>
        <input
          type="email"
          name="email"
          value={CoupleEmail}
          placeholder="   ex) email@gamil.com"
          style={{
            width: "350px",
            height: "40px",
            fontSize: "15px",
            left: "200px",
            backgroundColor: "rgba(202, 225, 245, 0.3)",
            borderRadius: "8px",
            border: "none",
            marginTop: "60px",
            marginLeft: "22px",
          }}
          onChange={InviteCoupleHandler}
        />
        <br />
        <button
          type="button"
          onChange={onSubmitHandler}
          formAction=""
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
