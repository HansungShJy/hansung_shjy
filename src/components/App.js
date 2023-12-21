import { BrowserRouter, Route, Routes } from "react-router-dom";
import Signup from "./Signup";
import Login from "./Login";
import InviteCouple from "./InviteCouple";
import Findid from "./Findid";
import Findpw from "./Findpw";
import JoinService from "./JoinService";
import Header from "./Header";
import Diary from "./Diary";
import Mypage from "./Mypage";
import Qna from "./Qna";
import Pay from "./Pay";
import Plan from "./Plan";
import Calendar from "./Calendar";
import PlanDetail from "./PlanDetail";
import QnaDetail from "./QnaDetail";

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
        <Route path="/diary" element={<Diary />} />
        <Route path="/qna" element={<Qna />} />
        <Route path="/plan" element={<Plan />} />
        <Route path="/pay" element={<Pay />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/calendar" element={<Calendar />} />
        <Route path="/plandetail" element={<PlanDetail />} />
        <Route path="/qnadetail/:qna_id" element={<QnaDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
