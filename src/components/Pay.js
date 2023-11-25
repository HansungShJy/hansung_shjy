import Header from "./Header";
import axios from "axios";
import { useCookies } from "react-cookie";
import React, { useState, useEffect, useRef } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import { Modal, Button } from "react-bootstrap";
import "./Pay.css";

function Pay() {
  const [showModal, setShowModal] = useState(false);
  const [paymethod, setPayMethod] = useState("");
  const [banktitle, setBankTitle] = useState("");
  const [money, setMoney] = useState("");
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;
  const calendarRef = useRef(null);
  const handleShowModal = () => setShowModal(true);
  const handleCloseModal = () => {
    setPayMethod("");
    setBankTitle("");
    setMoney("");
    setShowModal(false);
  };

  const [ViewData, setViewData] = useState([
    {
      id: "",
      title: "",
      start: "",
      method: "",
      paymoney: "",
    },
  ]);

  useEffect(() => {
    axios
      .get(`http://localhost:3000/pay`, {
        params: {
          userid: userid,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "33333");
        const formattedEvents = res.data.map((event) => ({
          title: event.bankTitle,
          start: new Date(event.bankDate),
          extendedProps: {
            method: event.payMethod,
            paymoney: event.money,
          },
          color: event.payMethod === false ? "#FAD9D9" : "rgba(255,233,37,0.5)",
        }));
        console.log(JSON.stringify(formattedEvents) + "evvvvvvv");
        setViewData(formattedEvents);
      })
      .catch((err) => {
        console.log(err + "::err");
      });
  }, [userid, paymethod]);

  const handleAddEvent = () => {
    if (paymethod === "" || banktitle === "" || money === "") {
      alert("빈칸을 모두 입력하세요.");
    } else {
      const newEvent = {
        title: banktitle,
        start: new Date(),
        extendedProps: {
          method: paymethod,
          paymoney: money,
        },
      };

      console.log(newEvent + "::newevent");
      console.log(userid);

      axios
        .post(`http://localhost:3000/pay/save`, {
          bankDate: new Date(),
          payMethod: paymethod,
          bankTitle: banktitle,
          money: money,
          userID: userid,
        })
        .then((response) => {
          console.log(JSON.stringify(response.data) + "::res");
          const calendarApi = calendarRef.current.getApi();
          calendarApi.addEvent(newEvent);
          calendarApi.refetchEvents();
          handleCloseModal();
        })
        .catch((err) => {
          console.log(err + "::err2");
        });
    }
  };

  console.log(calendarRef + "::ref");
  var today = new Date();
  var year = today.getFullYear();
  var month = ("0" + (today.getMonth() + 1)).slice(-2);
  var day = ("0" + today.getDate()).slice(-2);
  var dateString = year + "년 " + month + "월 " + day + "일";

  //dateClick(info) 날짜클릭 -> 수정하기

  return (
    <div>
      <Header />
      <div id="calendarBox">
        <FullCalendar
          ref={calendarRef}
          //eventDisplay="block"
          timeZone="UTC"
          initialView="dayGridMonth"
          plugins={[dayGridPlugin]}
          dayMaxEvents={true}
          //dayMaxEventRows={4}
          events={ViewData}
          headerToolbar={{
            start: "addEventButton",
            center: "title",
            end: "today,prev,next",
          }}
          customButtons={{
            addEventButton: {
              text: "지출 등록",
              click: handleShowModal,
            },
          }}
          editable={true}
          displayEventTime={false}
        />
      </div>
      <div className="Modal-background">
        <Modal
          show={showModal}
          onHide={handleCloseModal}
          backdrop="static"
          keyboard={false}
          bsPrefix="custom-modal"
        >
          <Modal.Header>
            <Modal.Title className="modal-title">
              {dateString}의 소비 내역을 입력해주세요.
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <form>
              <div className="form-group">
                <label className="pay-method-lb" htmlFor="pay-method">
                  입/출금
                </label>

                <label className="pay-detail-lb" htmlFor="pay-detail">
                  내역
                </label>
                <label className="pay-money-lb" htmlFor="pay-money">
                  금액
                </label>
                <br />
                <br />
                <select
                  type="dropbox"
                  className="pay-method-drop"
                  id="pay-method"
                  onChange={(e) => setPayMethod(e.target.value)}
                >
                  <option value="">선택해주세요</option>
                  <option value="true">입금</option>
                  <option value="false">출금</option>
                </select>

                <input
                  type="text"
                  className="pay-detail-ip"
                  id="pay-detail"
                  placeholder="  내용을 입력해주세요."
                  onChange={(e) => setBankTitle(e.target.value)}
                />

                <input
                  type="text"
                  className="pay-money-ip"
                  id="pay-money"
                  placeholder="  금액을 입력해주세요."
                  onChange={(e) => setMoney(e.target.value)}
                />
              </div>
            </form>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="close-modal"
              className="modal-close"
              onClick={handleCloseModal}
            >
              닫기
            </Button>
            <Button
              className="modal-addevent"
              variant="addevent-modal"
              onClick={handleAddEvent}
            >
              지출 등록하기
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
}

export default Pay;
