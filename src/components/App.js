import { BrowserRouter, Route, Routes } from "react-router-dom";
import Signup from "./Signup";
import Login from "./Login";
import InviteCouple from "./InviteCouple";
import Findid from "./Findid";
import Findpw from "./Findpw";
import JoinService from "./JoinService";
import Header from "./Header";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/Header" element={<Header />} />
        <Route path="/invitecouple" element={<InviteCouple />} />
        <Route path="/findid" element={<Findid />} />
        <Route path="/findpw" element={<Findpw />} />
        <Route path="/joinservice" element={<JoinService />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
