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
import PlanDetail from "./PlanDetail";
import QnaDetail from "./QnaDetail";
import PlanEdit from "./PlanEdit";
import DiaryDetail from "./DiaryDetail";
import DiaryList from "./DiaryList";
import "./App.css";

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
        <Route path="/plandetail" element={<PlanDetail />} />
        <Route path="/qnadetail/:qna_id" element={<QnaDetail />} />
        <Route path="/planedit/:planID" element={<PlanEdit />} />
        <Route path="/diarydetail" element={<DiaryDetail />} />
        <Route path="/diarylist" element={<DiaryList />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
