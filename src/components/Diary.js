import React, { useState, useEffect } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import axios from "axios";
import Header from "./Header";
import "./Diary.css";
import { useCookies } from "react-cookie";

function Diary() {
  const [cookies] = useCookies(["user_id"]);
  const [events, setEvents] = useState([]);
  const couple_id = cookies.user_id;
  useEffect(() => {
    axios
      .get(`http://localhost:3000/diary`, {
        params: {
          couple_id: couple_id,
        },
      })
      .then((res) => setEvents(res.data.event))
      .catch((err) => {
        console.log(typeof couple_id);

        console.log(err);
      });
  }, []);

  return (
    <div>
      <Header />
      <div className="Diary-main">
        <FullCalendar
          defaultView="dayGridMonth"
          height={"900px"}
          plugins={[dayGridPlugin]}
          contentHeight={"auto"}
          dayMaxEventRows={3}
          eventLimit={true}
          headerToolbar={{
            start: "today",
            center: "title",
            end: "prev,next",
          }}
        />
      </div>
    </div>
  );
}

export default Diary;
