import Header from "./Header";
import axios from "axios";
import { useCookies } from "react-cookie";
import React, { useState, useEffect, useRef } from "react";
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import { Modal, Button } from "react-bootstrap";
import "./Pay.css";

function Pay() {
  const [showModal, setShowModal] = useState(false);
  const [showModal2, setShowModal2] = useState(false);
  const [clickedDate, setClickedDate] = useState("");
  const [paymethod, setPayMethod] = useState("");
  const [banktitle, setBankTitle] = useState("");
  const [money, setMoney] = useState("");
  const [clickedEvent, setClickedEvent] = useState(null);
  const [isEditMode, setIsEditMode] = useState(false);
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;
  const calendarRef = useRef(null);
  const handleShowModal = () => setShowModal(true);
  const handleShowModal2 = () => setShowModal2(true);

  const handleCloseModal = () => {
    setPayMethod("");
    setBankTitle("");
    setMoney("");
    setShowModal(false);
  };
  const handleCloseModal2 = () => {
    setPayMethod("");
    setBankTitle("");
    setMoney("");
    setShowModal2(false);
  };

  const [ViewData, setViewData] = useState([
    {
      bankid: "",
      title: "",
      start: "",
      method: "",
      paymoney: "",
    },
  ]);

  const [ViewDataList, setViewDataList] = useState([
    {
      bankid: "",
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
        //console.log(JSON.stringify(res.data) + "33333");
        const formattedEvents = res.data.map((event) => ({
          title: event.bankTitle,
          start: new Date(event.bankDate),
          extendedProps: {
            method: event.payMethod,
            paymoney: event.money,
          },
          color: event.payMethod === false ? "#FAD9D9" : "rgba(255,233,37,0.5)",
        }));
        //console.log(JSON.stringify(formattedEvents) + "evvvvvvv");
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
          //console.log(JSON.stringify(response.data) + "::res");
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
  //var bankDate = year + "-" + month + "-" + day;

  //dateClick(info) 날짜클릭 -> 수정하기
  const handlelistEvent = (info) => {
    //alert("클릭했습니다.");
    const bankDate = info.date.toISOString().substring(0, 10);
    console.log(typeof info.date.toISOString().substring(0, 10));

    setClickedDate(bankDate);

    axios
      .get(`http://localhost:3000/pay/detail/${bankDate}`, {
        headers: { "Content-Type": "application/json" },
        params: {
          userid: userid,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::res");

        const formattedEvents = res.data.map((event) => ({
          title: event.bankTitle,
          start: new Date(event.bankDate),
          extendedProps: {
            method: event.payMethod,
            paymoney: event.money,
            bankid: event.bankID,
          },
          color: event.payMethod === false ? "#FAD9D9" : "rgba(255,233,37,0.5)",
        }));
        setViewDataList(formattedEvents);
        handleShowModal2();
      })
      .catch((err) => {
        console.log(err + "::err-detail");
      });
  };

  return (
    <div>
      <Header />
      <div id="calendarBox">
        <FullCalendar
          ref={calendarRef}
          //eventDisplay="block"
          timeZone="UTC"
          initialView="dayGridMonth"
          plugins={[dayGridPlugin, interactionPlugin]}
          dayMaxEvents={true}
          events={ViewData}
          dateClick={handlelistEvent}
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

        <div className="Modal-2222222222222222222">
          <Modal
            show={showModal2}
            onHide={handleCloseModal2}
            bsPrefix="custom-modal2"
          >
            <Modal.Header>
              <Modal.Title className="modal-title2">{clickedDate}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              {ViewDataList.map((event) => (
                <div key={event.bankid}>
                  <span
                    className="event-back"
                    style={{ backgroundColor: event.color }} //color or backgroundColor밖에 안됨
                  >
                    <span className="event-list">
                      {/* {event.extendedProps && event.extendedProps.method
                        ? "입금"
                        : "출금"}{" "} */}
                      {event.title}{" "}
                      {/* {event.extendedProps ? event.extendedProps.paymoney : ""} */}
                      {/* <input
                        type="text"
                        placeholder={event.extendedProps.paymoney}
                      ></input> */}
                    </span>

                    <hr color="#d9d9d9" />
                  </span>
                </div>
              ))}
            </Modal.Body>
            <Modal.Footer>
              <Button
                variant="close-modal"
                className="modal-close2"
                onClick={handleCloseModal2}
              >
                닫기
              </Button>
              <Button
                className="edit-modal"
                variant="edit-event"
                //onClick={handleAddEvent}
              >
                수정하기
              </Button>
            </Modal.Footer>
          </Modal>
        </div>
      </div>
    </div>
  );
}

export default Pay;
