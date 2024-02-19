import React from "react";
import DaumPostcode from "react-daum-postcode";

const Post = (props) => {
  const complete = (data) => {
    let fullAddress = data.address;
    let extraAddress = "";

    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress +=
          extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }
    console.log(data);
    console.log(fullAddress);
    console.log(data.zonecode);

    props.setcompany({
      ...props.company,
      address: fullAddress,
    });
  };

  return (
    <div>
      <DaumPostcode
        className="postmodal"
        style={{
          background: "rgba(0,0,0,0.25)",
          position: "fixed",
          left: "0",
          top: "0",
          height: "100%",
          width: "100%",
        }}
        autoClose
        onComplete={complete}
      />
    </div>
  );
};

export default Post;
