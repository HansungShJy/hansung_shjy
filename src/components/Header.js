import React, { useState } from "react";
import Nav from "./\bNav";
import "./Header.css";
import { Cookies } from "react-cookie";
import homelogo from "../assets/homelogoset.png";

function Header() {
  const userId = useState("");
  const cookie = new Cookies();

  return (
    <div>
      <div className="header-logo">
        <img src={homelogo} alt="homelogoset.png" width="340" />
      </div>

      <Nav />
    </div>
  );
}

export default Header;
