console.log("admin js running")

document
.querySelector("#image-file-input")
.addEventListener("change",function (event) {

    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function () {
        document
        .querySelector("#upload-image-preview")
        .setAttribute("src",reader.result);
    };
    reader.readAsDataURL(file);
});