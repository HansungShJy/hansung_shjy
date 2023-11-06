import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import Header from "./Header";
import "./Diary.css";

function Diary() {
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
          events={[
            {
              title: "태국 여행",
              start: "2023-11-13",
              end: "2023-11-14",
              color: "#b1aee5",
            },
            {
              title: "베트남 여행",
              start: "2023-11-18",
              end: "2023-11-23",
              color: "#b1aee5",
            },
            { title: "판매건수 : 23건", date: "2023-11-06" },
          ]}
        />
      </div>
    </div>
  );
}

export default Diary;
