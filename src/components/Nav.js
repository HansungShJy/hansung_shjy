import { NavLink } from "react-router-dom";
import React from "react";
import "./Nav.css";

function Nav() {
  return (
    <div>
      <div className="navbar">
        <NavLink
          to="/diary"
          className="navbarMenu"
          style={({ isActive }) => ({
            backgroundColor: isActive ? "rgba(202, 225, 245, 0.5)" : "white",
            borderRadius: isActive ? "30px" : "0px",
          })}
        >
          캘린더
        </NavLink>
        <NavLink
          className="navbarMenu"
          to="/qna"
          style={({ isActive }) => ({
            backgroundColor: isActive ? "rgba(202, 225, 245, 0.5)" : "white",
            borderRadius: isActive ? "30px" : "0px",
          })}
        >
          오늘의 질문
        </NavLink>
        <NavLink
          className="navbarMenu"
          to="/pay"
          style={({ isActive }) => ({
            backgroundColor: isActive ? "rgba(202, 225, 245, 0.5)" : "white",
            borderRadius: isActive ? "30px" : "0px",
          })}
        >
          우리의 지출
        </NavLink>
        <NavLink
          className="navbarMenu"
          to="/plan"
          style={({ isActive }) => ({
            backgroundColor: isActive ? "rgba(202, 225, 245, 0.5)" : "white",
            borderRadius: isActive ? "30px" : "0px",
          })}
        >
          우리의 여행 계획
        </NavLink>
        <NavLink
          className="navbarMenu"
          to="/mypage"
          style={({ isActive }) => ({
            backgroundColor: isActive ? "rgba(202, 225, 245, 0.5)" : "white",
            borderRadius: isActive ? "30px" : "0px",
          })}
        >
          마이페이지
        </NavLink>
      </div>
    </div>
  );
}

export default Nav;
