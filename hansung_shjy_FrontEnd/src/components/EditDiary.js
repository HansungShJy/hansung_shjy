import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import Header from "./Header";
import "./EditDiary.css";
import { useCookies } from "react-cookie";
import { useLocation } from "react-router-dom";

function EditDiary() {
  const location = useLocation();
  const diary_id = location.state?.diary_id;
  const [cookies] = useCookies(["couple_id", "user_id", "nickname"]);
  const couple_id = cookies.couple_id;
  const user_id = cookies.user_id;
  const [diarydate, setDiaryDate] = useState(Date);
  const [usercontent, setUserContent] = useState("");
  const [othercontent, setOtherContent] = useState("");
  const [userNN, setUserNN] = useState("");
  const [otherNN, setOtherNN] = useState("");
  const [userID1, setUserID1] = useState("");
  const [userID2, setUserID2] = useState("");
  const encodedNickname = decodeURIComponent(cookies.nickname);
  const [file, setFile] = useState(null);
  const thumbnailInput = useRef();
  const [imageSrc, setImageSrc] = useState("");
  const [uploadimg, setUploadImg] = useState(null);

  const onDiaryDate = (e) => {
    setDiaryDate(e.target.value);
  };
  const onDiaryUser = (e) => {
    setUserContent(e.target.value);
  };
  const onDiaryOther = (e) => {
    setOtherContent(e.target.value);
  };

  const encodeFileToBase64 = (fileBlob) => {
    const reader = new FileReader();
    const formData = new FormData();
    const uploadFile = fileBlob;
    formData.append("file", uploadFile);
    setFile(uploadFile);

    reader.readAsDataURL(fileBlob);

    return new Promise((resolve) => {
      reader.onload = () => {
        const img = new Image();
        img.src = reader.result;

        img.onload = () => {
          const canvas = document.createElement("canvas");
          const MAX_WIDTH = 800;
          const MAX_HEIGHT = 600;
          let width = img.width;
          let height = img.height;

          if (width > height) {
            if (width > MAX_WIDTH) {
              height *= MAX_WIDTH / width;
              width = MAX_WIDTH;
            }
          } else {
            if (height > MAX_HEIGHT) {
              width *= MAX_HEIGHT / height;
              height = MAX_HEIGHT;
            }
          }

          canvas.width = width;
          canvas.height = height;

          const ctx = canvas.getContext("2d");
          ctx.drawImage(img, 10, 10, width, height);

          const resizedBase64 = canvas.toDataURL("image/jpeg");
          setImageSrc(resizedBase64);
          resolve();
        };
      };
    });
  };
  useEffect(() => {
    axios
      .get(`http://localhost:3000/diary/detail/${diary_id}`, {
        params: {
          couple_id: couple_id,
        },
      })
      .then((res) => {
        const diaryDetail = res.data.diaryDetail;
        //console.log(JSON.stringify(diaryDetail) + "::first res");
        setUploadImg(res.data.image);

        const { diaryDate, myDiary, otherDiary } = diaryDetail;

        setDiaryDate(diaryDate);
        setUserContent(myDiary || "");
        setOtherContent(otherDiary || "");

        setUserNN(res.data.nickname1);
        setOtherNN(res.data.nickname2);
        setUserID1(res.data.userID1);
        setUserID2(res.data.userID2);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [diary_id, couple_id]);

  const DiarySave = (e) => {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("diaryDate", diarydate);
    formData.append("myDiary", usercontent);
    formData.append("otherDiary", othercontent);
    formData.append("userID", user_id);
    axios
      .patch(`http://localhost:3000/diary/edit/${diary_id}`, formData)
      .then((response) => {
        console.log(response);
        document.location.href = "/diary";
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <Header />

      <div>
        <button className="diarysave_btn" onClick={(e) => DiarySave(e)}>
          일기 수정
        </button>
        <div className="diary_div">
          <h4 className="diarydate_lb">오늘의 날짜</h4>
          <input
            type="date"
            className="diarydate_ip"
            value={diarydate}
            onChange={onDiaryDate}
          />
        </div>

        <h4 className="diaryphoto_lb">사진 등록</h4>
        <form
          className="file_upload"
          name="form"
          method="post"
          encType="multipart/form-data"
        >
          {imageSrc ? (
            <img src={imageSrc} alt="preview-img" />
          ) : (
            <img src={`data:image/png;base64,${uploadimg}`} alt="uploadimg" />
          )}
          <br />
          <input
            type="file"
            name="image"
            accept="image/*"
            multiple
            ref={thumbnailInput}
            onChange={(e) => {
              encodeFileToBase64(e.target.files[0]);
            }}
          />
        </form>
        <br />
        <h4 className="diary_lb">오늘의 일기</h4>
        <label className="DiaryD_lb">
          {" "}
          {decodeURIComponent(encodedNickname)} 님의 기록
        </label>
        <label className="DiaryD1_lb">
          {encodedNickname === userNN ? otherNN : userNN} 님의 기록
        </label>
        <br />
        <textarea
          className="diaryuser_txt"
          placeholder={Number(userID2) === user_id ? othercontent : usercontent}
          onChange={onDiaryUser}
        />

        <textarea
          disabled
          className="diaryother_txt"
          placeholder={Number(userID1) === user_id ? othercontent : usercontent}
          value={othercontent}
          onChange={onDiaryOther}
        />
      </div>
    </div>
  );
}

export default EditDiary;
