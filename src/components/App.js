import { BrowserRouter, Route, Routes } from "react-router-dom";
import Signup from "./Signup";
import Login from "./Login";
import InviteCouple from "./InviteCouple";
import Findid from "./Findid";
import Findpw from "./Findpw";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/invitecouple" element={<InviteCouple />} />
        <Route path="/findid" element={<Findid />} />
        <Route path="/findpw" element={<Findpw />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
