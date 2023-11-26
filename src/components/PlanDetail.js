import { useState } from "react";
import Header from "./Header";
import "./PlanDetail.css";
import Post from "./Post";

function PlanDetail() {
  const [planTitle, setPlanTitle] = useState("");
  const [startDate, setStartDate] = useState(Date);
  const [endDate, setEndDate] = useState(Date);
  const [traffic, setTraffic] = useState("");
  const [planDHome, setPlanDHome] = useState("");
  const [location, setLocation] = useState("");
  const [planPrice, setPlanPrice] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [checkedbox, setCheckedBox] = useState(false);
  const [enroll_company, setEnroll_company] = useState({
    address: "",
  });

  const [popup, setPopup] = useState(false);

  const onPlanHome = (e) => {
    setEnroll_company({
      ...enroll_company,
      [e.target.name]: e.target.value,
    });
  };

  const handleComplete = (data) => {
    setPopup(!popup);
  };

  const onPlanTitle = (e) => {
    setPlanTitle(e.target.value);
  };

  const onPlanStart = (e) => {
    setStartDate(e.target.value);
  };

  const onPlanEnd = (e) => {
    setEndDate(e.target.value);
  };

  const onTrafficBox = (e) => {
    setTraffic(e.target.value);
  };

  const onPlanDHome = (e) => {
    setPlanDHome(e.target.value);
  };

  const onLocation = (e) => {
    setLocation(e.target.value);
  };

  const onPlanPrice = (e) => {
    setPlanPrice(e.target.value);
  };

  const onStartTime = (e) => {
    setStartTime(e.target.value);
  };

  const onEndTime = (e) => {
    setEndTime(e.target.value);
  };

  const onCheckBox = (e) => {
    setCheckedBox(e.target.value);
  };

  return (
    <div>
      <Header />
      <div className="plan_Detail_main">
        <label className="title-lb">이번 여행 제목</label>
        <br />
        <input
          className="title-ip"
          type="text"
          userName="PlanTitle"
          value={planTitle}
          onChange={onPlanTitle}
        />
        <br />
        <label className="date-lb">날짜</label>
        <label className="trans-lb">교통</label>
        <label className="home-lb">숙소</label>
        <br />
        <input
          type="date"
          className="plan-start-ip"
          userName="plan_start"
          value={startDate}
          onChange={onPlanStart}
        />
        <label
          style={{
            fontSize: "17px",
            marginLeft: "16px",
          }}
        >
          ~
        </label>
        <input
          type="date"
          userName="plan_end"
          className="plan-end-ip"
          value={endDate}
          onChange={onPlanEnd}
        />
        <select
          type="dropbox"
          className="transfer_box"
          id="planTraffic"
          onChange={onTrafficBox}
        >
          <option value="" className="select_box">
            선택해주세요
          </option>
          <option value="0">버스</option>
          <option value="1">지하철</option>
          <option value="2">자동차</option>
          <option value="3">KTX</option>
          <option value="4">비행기</option>
        </select>

        <input
          type="text"
          placeholder="도로명 주소"
          className="home-ip"
          userName="PlanHome"
          required={true}
          value={enroll_company.address}
          onChange={onPlanHome}
        />

        <input
          type="text"
          placeholder="상세 주소"
          className="homedetail-ip"
          userName="PlanHome"
          value={planDHome}
          onChange={onPlanDHome}
        />

        <button className="location-btn" type="button" onClick={handleComplete}>
          주소찾기
        </button>
        {popup && (
          <Post company={enroll_company} setcompany={setEnroll_company}></Post>
        )}

        <br />
        <label className="day1_plan">1일차</label>
        <br />
        <label className="location-lb">위치</label>
        <label className="price-lb">가격</label>
        <label className="time-lb">시간</label>
        <label className="reservation-lb">예약</label>
        <br />
        <label className="day1-1-lb">#1.</label>
        <input
          type="text"
          className="location-ip"
          userName="PlanLocation"
          value={location}
          onChange={onLocation}
        />
        <input
          type="text"
          userName="planPrice"
          className="price-ip"
          value={planPrice}
          onChange={onPlanPrice}
        />
        <label
          style={{
            fontSize: "13px",
            fontWeight: "bold",
            marginLeft: "5px",
          }}
        >
          원
        </label>
        <input
          type="time"
          className="timestart-ip"
          userName="planTime_start"
          value={startTime}
          onChange={onStartTime}
        />
        <label
          style={{
            fontSize: "17px",
            marginLeft: "16px",
          }}
        >
          ~
        </label>
        <input
          type="time"
          className="timeend-ip"
          userName="planTime_end"
          value={endTime}
          onChange={onEndTime}
        />
        <input
          type="checkbox"
          value={checkedbox}
          style={{ marginLeft: "45px" }}
          onChange={onCheckBox}
        />
        <br />
        <button className="plusplan-btn" type="button">
          일정추가
        </button>
      </div>
    </div>
  );
}

export default PlanDetail;
