import { useState, useRef, useEffect } from "react";
import Header from "./Header";
import "./PlanEdit.css";
import Post from "./Post";
import axios from "axios";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import { useCookies } from "react-cookie";

export function PlanEdit() {
  const uselocation = useLocation();
  const title = uselocation.state?.title;
  const start = uselocation.state?.start;
  const end = uselocation.state?.end;
  const trafficedit = uselocation.state?.trafficedit;
  const planHome = uselocation.state?.planHome;
  const [planTitle, setPlanTitle] = useState("");
  const [startDate, setStartDate] = useState(Date);
  const [endDate, setEndDate] = useState(Date);
  const [traffic, setTraffic] = useState(trafficedit);
  const [planDHome, setPlanDHome] = useState("");
  const [planNumber, setPlanNumber] = useState(0);
  const [location, setLocation] = useState("");
  const [planPrice, setPlanPrice] = useState(0);
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [checkedbox, setCheckedBox] = useState(false);
  const [planDetailID, setplanDetailID] = useState("");
  const { planID } = useParams();

  const [popup, setPopup] = useState(false);
  const [cookies] = useCookies(["couple_id"]);
  const couple_id = cookies.couple_id;
  const navigate = useNavigate();
  const calendarRef = useRef(null);
  const [locations, setLocations] = useState([]);

  const [enroll_company, setEnroll_company] = useState({
    address: "",
  });

  useEffect(() => {
    axios
      .get(`http://localhost:3000/plan/detail/${planID}`)
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::res");
        const resData = res.data;

        const newLocations = resData.planDetail.map((detail) => ({
          planDetailID: detail.planDetailID,
          planNumber: detail.planNumber,
          location: detail.planLocation,
          planPrice: detail.planPrice,
          startTime: detail.planStartTime,
          endTime: detail.planEndTime,
          checkedbox: detail.planCheck,
        }));

        setLocations(newLocations);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [planID, couple_id]);

  const onPlanHome = (e) => {
    setEnroll_company({
      ...enroll_company,
      [e.target.name]: e.target.value,
    });
  };

  const handleLocation = () => {
    setLocations([
      ...locations,
      {
        planDetailID: "",
        planNumber: Number(planNumber) + 1,
        location: "",
        planPrice: "",
        startTime: "",
        endTime: "",
        checkedbox: false,
      },
    ]);
    setPlanNumber(planNumber + 1);
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
    console.log(e);
    var traffic = document.getElementById("planTraffic");
    var value = traffic.options[trafficedit.selectedIndex].text;
    return value;
  };

  const onTraffic = (e) => {
    setTraffic(e.target.value);
  };

  const onPlanDHome = (e) => {
    setPlanDHome(e.target.value);
  };
  const onLocation = (index, e) => {
    const newLocations = [...locations];
    newLocations[index].location = e.target.value;
    setLocations(newLocations);
  };

  const onPlanPrice = (index, e) => {
    const newLocations = [...locations];
    newLocations[index].planPrice = e.target.value;
    setLocations(newLocations);
  };

  const onStartTime = (index, e) => {
    const newLocations = [...locations];
    newLocations[index].startTime = e.target.value;
    setLocations(newLocations);
  };

  const onEndTime = (index, e) => {
    const newLocations = [...locations];
    newLocations[index].endTime = e.target.value;
    setLocations(newLocations);
  };

  const onCheckBox = (index, e) => {
    const newLocations = [...locations];
    newLocations[index].checkedbox = e.target.checked ? 1 : 0;
    setLocations(newLocations);
  };

  const handleCancel = () => {
    if (
      window.confirm(
        "여행 계획을 취소하시겠습니까? 현재 작성된 정보가 사라집니다."
      )
    ) {
      navigate("/plan");
    }
  };

  const handleEditPlan = () => {
    console.log(
      "빈칸 확인:",
      planTitle,
      startDate,
      endDate,
      traffic,
      planDHome,
      enroll_company.address,
      planPrice,
      location,
      startTime,
      endTime,
      locations
    );

    if (
      startDate === "" ||
      endDate === "" ||
      traffic === "" ||
      planDHome === "" ||
      enroll_company.address === "" ||
      locations.some(
        (loc) =>
          loc.location === "" ||
          loc.planPrice === "" ||
          loc.startTime === "" ||
          loc.endTime === ""
      )
    ) {
    } else {
      const newEvent = {
        title: planTitle,
        start: startDate,
        end: endDate,

        extendedProps: {
          traffic: traffic,
          planHome: enroll_company.address + planDHome,
          location: location,
          planPrice: planPrice,
          starttime: startTime,
          endtime: endTime,
          checkbox: checkedbox ? 1 : 0,
        },
      };
      console.log(newEvent + "::newevent");

      axios
        .patch(`http://localhost:3000/plan/edit/${planID}`, {
          planDTO: {
            planTitle: planTitle,
            //planStartDate: startDate,
            //planEndDate: endDate,
            planTraffic: traffic,
            planHome: enroll_company.address + planDHome,
            coupleID: couple_id,
          },
          planDetailDTO: locations.map((loc) => ({
            planDetailID: loc.planDetailID,
            planNumber: loc.planNumber,
            planLocation: loc.location,
            planPrice: loc.planPrice,
            planDayNumber: 1,
            //planStartTime: loc.startTime,
            //planEndTime: loc.endTime,
            planCheck: loc.checkedbox,
          })),
        })
        .then((res) => {
          console.log(JSON.stringify(res.data) + "::res");
          //console.log(res.data + "res");

          navigate("/plan");
        })
        .catch((err) => {
          console.log(err + "::err2");
        });
    }
  };
  //console.log(calendarRef.data + "::ref");

  return (
    <div>
      <Header />
      <div className="plan_Detail_main">
        <label className="title-lb">이번 여행 제목</label>

        <input
          className="title-ip"
          type="text"
          userName="PlanTitle"
          value={planTitle}
          onChange={onPlanTitle}
          placeholder={title}
        ></input>

        <br />
        <label className="date-lb">날짜</label>
        <label className="trans-lb">교통</label>
        <label className="home-lb">숙소</label>
        <br />
        <input
          type="date"
          className="plan-start-ip"
          userName="plan_start"
          value={start}
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
          value={end}
          onChange={onPlanEnd}
        />
        <select
          type="dropbox"
          className="transfer_box"
          id="planTraffic"
          //defaultValue={onTrafficBox}
          onChange={onTraffic}
          value={traffic}
        >
          <option value="" className="select_box">
            선택해주세요
          </option>
          <option name="traffic" value="0">
            버스
          </option>
          <option name="traffic" value="1">
            지하철
          </option>
          <option name="traffic" value="2">
            자동차
          </option>
          <option name="traffic" value="3">
            KTX
          </option>
          <option name="traffic" value="4">
            비행기
          </option>
        </select>

        <input
          type="text"
          placeholder={planHome}
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
        {locations.map((location, index) => (
          <div className="plan_date_loop" key={index}>
            <label className="day1-1-lb">#{location.planNumber}.</label>
            <input
              type="text"
              className="location-ip"
              userName="PlanLocation"
              value={location.location}
              onChange={(e) => onLocation(index, e)}
              placeholder={location.planNumber.location}
            />
            <input
              type="text"
              userName="planPrice"
              className="price-ip"
              value={location.planPrice}
              onChange={(e) => onPlanPrice(index, e)}
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
              value={location.startTime}
              onChange={(e) => onStartTime(index, e)}
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
              value={location.endTime}
              onChange={(e) => onEndTime(index, e)}
            />
            <input
              type="checkbox"
              value={location.checkedbox}
              style={{ marginLeft: "45px" }}
              onChange={(e) => onCheckBox(index, e)}
            />
          </div>
        ))}

        <br />
        <button className="plusplan-btn" type="button" onClick={handleLocation}>
          일정추가
        </button>
        <br />
        <button className="plansave-btn" type="button" onClick={handleEditPlan}>
          여행계획 수정
        </button>
        <button className="plancancle-btn" type="button" onClick={handleCancel}>
          취소
        </button>
      </div>
    </div>
  );
}

export default PlanEdit;
