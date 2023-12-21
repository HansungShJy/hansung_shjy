import Header from "./Header";
import axios from "axios";
import { useCookies } from "react-cookie";
import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import { Modal, Button } from "react-bootstrap";
import "./Plan.css";
import delete_icon from "../assets/delete_icon.png";

function Plan() {
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;
  const [events, setEvents] = useState([]);
  const [plan_id, setPlanID] = useState(null);
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate();

  const handleShowModal = (event) => {
    setShowModal(true);
    setSelectedEvent(event);
    console.log(event);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleAddPlan = () => {
    navigate("/planDetail");
  };

  const [PlanData, setPlanData] = useState([
    {
      planid: "",
      title: "",
      start: "",
      end: "",
    },
  ]);

  const handleDeletePlan = () => {
    //planid가 첫번째로 지정되어있음.
    if (window.confirm("여행 계획을 삭제하시겠습니까?")) {
      setPlanID(selectedEvent.extendedProps.planID);
      console.log(selectedEvent.extendedProps.planID);
      axios
        .delete(`http://localhost:3000/plan/delete/${plan_id}`)
        .then((res) => {
          console.log(res + "여행계획 삭제 완");

          setPlanData((prevData) =>
            prevData.filter(
              (item) =>
                item.extendedProps.planid !== selectedEvent.extendedProps.planID
            )
          );
          document.location.href = "./plan";
        })
        .catch((error) => {
          console.error(error + "여행계획 삭제 실패");
        });
    } else {
      return;
    }
  };

  const convertResponseToEvents = (res) => {
    const convertedEvents = res.map((data) => ({
      title: data.planTitle,
      start: data.planStartDate,
      end: data.planEndDate,
      color: getRandomColor(),
      extendedProps: {
        traffic: data.planTraffic,
        planHome: data.planHome,
        userID: data.userID,
        planID: data.planID,
      },
    }));

    setPlanData(convertedEvents);
    setSelectedEvent(convertedEvents.extendedProps.planID);
    return convertedEvents;
  };
  function getRandomColor() {
    const colors = ["#E67A50", "#3688D8", "#E67D9B", "#CDE66E", "#8B87E5"];
    const randomIndex = Math.floor(Math.random() * colors.length);
    return colors[randomIndex];
  }
  const eventContent = (arg) => {
    return (
      <div style={{ height: "5px" }}>
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
  }, [userid, showModal]);

  return (
    <div>
      <Header />
      <FullCalendar
        eventContent={eventContent}
        className="plan-calendar"
        timeZone="UTC"
        initialView="dayGridMonth"
        plugins={[dayGridPlugin, interactionPlugin]} //interaction - dateClick 삭제하기.
        dayMaxEvents={2}
        events={events}
        //eventClick={planEditEvent}
        //dateClick={handleDeletePlan}
        headerToolbar={{
          start: "addEventButton,deleteEventButton",
          center: "title",
          end: "today,prev,next",
        }}
        customButtons={{
          addEventButton: {
            text: "여행 등록",
            click: handleAddPlan,
          },
          deleteEventButton: {
            className: "deletebutton",
            text: "여행 삭제",
            click: handleShowModal,
          },
        }}
        editable={true}
        displayEventTime={false}
      />
      <div>
        <Modal
          show={showModal}
          onHide={handleCloseModal}
          backdrop="static"
          keyboard={false}
          bsPrefix="plan-modal"
        >
          <Modal.Header>
            <Modal.Title className="modal-title2">
              여행 계획
              <Button
                variant="close-modal"
                className="planmodal-close"
                onClick={handleCloseModal}
              >
                X
              </Button>
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {events.map((event) => (
              <div key={event.extendedProps.planID} className="planD">
                <label className="planD_title_lb"> {event.title} </label>
                <label className="planD_start_lb">{event.start}</label>
                <label> ~ </label>
                <label className="planD_end_lb">{event.end}</label>
                <img
                  className="delete_icon"
                  src={delete_icon}
                  alt="delete_icon.png"
                  width="20"
                  onClick={() => handleDeletePlan(event)}
                />
              </div>
            ))}
          </Modal.Body>
          <Modal.Footer></Modal.Footer>
        </Modal>
      </div>
    </div>
  );
}

export default Plan;
