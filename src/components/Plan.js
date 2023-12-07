import Header from "./Header";
import axios from "axios";
import { useCookies } from "react-cookie";
import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import "./Plan.css";

function Plan() {
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;
  const [events, setEvents] = useState([]);
  const [plan_id, setPlanID] = useState(null);
  const navigate = useNavigate();

  const handleAddPlan = () => {
    navigate("/planDetail");
  };

  const planEditEvent = () => {
    if (window.confirm("여행 계획을 수정하시겠습니까?")) {
      navigate("/planDetail", { state: { planID: plan_id } });
    }
    axios
      .delete(`http://localhost:3000//plan/edit/${plan_id}`)
      .then((res) => {
        console.log(res + "여행계획 수정 완");
      })
      .catch((error) => {
        console.error(error + "여행계획 수정 실패");
      });
  };

  const convertResponseToEvents = (response) => {
    const convertedEvents = response.map((data) => ({
      title: data.planTitle,
      start: data.planStartDate,
      end: data.planEndDate,
      extendedProps: {
        traffic: data.planTraffic,
        planHome: data.planHome,
        userID: data.userID,
        planID: data.planID,
      },
    }));

    setPlanID(convertedEvents[0].extendedProps.planID); // 이 부분을 반환문 앞으로 이동

    return convertedEvents;
  };

  const eventContent = (arg) => {
    return (
      <div style={{ height: "5px" }}>
        {arg.timeText}
        <b>{arg.event.title}</b>
      </div>
    );
  };
  useEffect(() => {
    axios
      .get(`http://localhost:3000/plan`, {
        params: {
          userid: userid,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::res");
        const events = convertResponseToEvents(res.data);
        setEvents(events);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userid]);

  return (
    <div>
      <Header />
      <FullCalendar
        eventContent={eventContent}
        className="plan-calendar"
        timeZone="UTC"
        initialView="dayGridMonth"
        plugins={[dayGridPlugin, interactionPlugin]} //interaction - dateClick 삭제하기.
        dayMaxEvents={true}
        events={events}
        eventClick={planEditEvent}
        //dateClick={handleDeletePlan}
        headerToolbar={{
          start: "addEventButton",
          center: "title",
          end: "today,prev,next",
        }}
        customButtons={{
          addEventButton: {
            text: "여행 등록",
            click: handleAddPlan,
          },
        }}
        editable={true}
        displayEventTime={false}
      />
    </div>
  );
}

export default Plan;
