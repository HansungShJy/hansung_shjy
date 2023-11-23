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
        }));
        console.log(JSON.stringify(formattedEvents) + "evvvvvvv");
        setViewData(formattedEvents);
      })
      .catch((err) => {
        console.log(err + "::err");
      });
  }, [userid]);

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
          timeZone="UTC"
          initialView="dayGridMonth"
          plugins={[dayGridPlugin]}
          dayMaxEventRows={4}
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
          style={{
            backgroundColor: "white",
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            borderRadius: "15px",
            maxWidth: "80%",
          }}
        >
          <Modal.Header>
            <Modal.Title className="modal-title">
              {dateString}의 소비 내역을 입력해주세요.
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <form>
              <div className="form-group">
                <label
                  htmlFor="pay-method"
                  style={{
                    fontSize: "20px",
                    fontWeight: "bold",
                    marginLeft: "120px",
                  }}
                >
                  입/출금
                </label>

                <label
                  htmlFor="pay-detail"
                  style={{
                    fontSize: "20px",
                    fontWeight: "bold",
                    marginLeft: "155px",
                  }}
                >
                  내역
                </label>
                <label
                  htmlFor="pay-money"
                  style={{
                    fontSize: "20px",
                    fontWeight: "bold",
                    marginLeft: "185px",
                  }}
                >
                  금액
                </label>
                <br />
                <br />
                <select
                  type="dropbox"
                  className="form-control"
                  id="pay-method"
                  style={{
                    fontWeight: "bold",
                    marginLeft: "100px",
                    width: "120px",
                    height: "23px",
                    outline: "none",
                    border: "0",
                  }}
                  onChange={(e) => setPayMethod(e.target.value)}
                >
                  <option value="">선택해주세요</option>
                  <option value="true">입금</option>
                  <option value="false">출금</option>
                </select>

                <input
                  type="text"
                  className="form-control"
                  id="pay-detail"
                  placeholder="  내용을 입력해주세요."
                  style={{
                    borderRadius: "6px",
                    marginLeft: "60px",
                    borderColor: "#D9D9D9",
                    width: "150px",
                    height: "25px",
                    fontSize: "13px",
                    fontWeight: "bold",
                    outline: "none",
                  }}
                  onChange={(e) => setBankTitle(e.target.value)}
                />

                <input
                  type="text"
                  className="form-control"
                  id="pay-money"
                  placeholder="  금액을 입력해주세요."
                  style={{
                    borderRadius: "6px",
                    marginLeft: "60px",
                    borderColor: "#D9D9D9",
                    width: "150px",
                    height: "25px",
                    fontSize: "13px",
                    fontWeight: "bold",
                    outline: "none",
                  }}
                  onChange={(e) => setMoney(e.target.value)}
                />
              </div>
            </form>
          </Modal.Body>
          <Modal.Footer>
            <Button
              variant="secondary"
              onClick={handleCloseModal}
              style={{
                fontSize: "15px",
                fontWeight: "bold",
                width: "230px",
                height: "30px",
                backgroundColor: "rgba(175, 205, 245, 0.7)",
                color: "000000",
                border: "0px",
                borderRadius: "10px",
                marginTop: "10px",
                marginLeft: "100px",
              }}
            >
              닫기
            </Button>
            <Button
              variant="primary"
              onClick={handleAddEvent}
              style={{
                fontSize: "15px",
                fontWeight: "bold",
                width: "230px",
                height: "30px",
                backgroundColor: "rgba(175, 205, 245, 0.7)",
                color: "000000",
                border: "0px",
                borderRadius: "10px",
                marginLeft: "40px",
              }}
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
