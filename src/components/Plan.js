import Header from "./Header";
import axios from "axios";
import { useCookies } from "react-cookie";
import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import { PlanDetail } from "./PlanDetail";

function Plan() {
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;
  const [events, setEvents] = useState([]);
  const navigate = useNavigate();

  const handleAddPlan = () => {
    navigate("/planDetail");
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
        setEvents(PlanDetail.plandata);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [userid]);

  return (
    <div>
      <Header />

      <FullCalendar
        timeZone="UTC"
        initialView="dayGridMonth"
        plugins={[dayGridPlugin, interactionPlugin]} //interaction - dateClick 삭제하기.
        dayMaxEvents={true}
        events={events}
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
