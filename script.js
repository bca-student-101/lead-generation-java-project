//logo
const logo=document.querySelector(".logo");
logo.addEventListener("click",function(){
alert("Welcome to Lead Generation Website");
});

//nav
const navBtn = document.getElementById("navConsultBtn");
navBtn.addEventListener("click", function () {
    alert("hello")
    document.getElementById("contact").scrollIntoView({
        behavior: "smooth"
    });
});



const consultBtn = document.getElementById("consultBtn");
consultBtn.addEventListener("click", function () {
    alert("Thank you! Our team will contact you soon.");
});



// // Our Lead Generation Process Button
// const processBtn = document.getElementById("processBtn");

// processBtn.addEventListener("click", function () {
//     document.getElementById("process").scrollIntoView({
//         behavior: "smooth"
//     });
// });
const processBtn = document.getElementById("processBtn");
processBtn.addEventListener("click", function () {
    alert("Opening Lead Generation Process...");
});


//card popup
const cards = document.querySelectorAll(".card");
cards.forEach(function(card){
    card.addEventListener("mouseover", function(){
        card.style.transform = "translateY(-12px)";
        card.style.boxShadow = "0 15px 30px rgba(0,0,0,0.15)";
        card.style.transition = "0.3s ease";
        card.style.borderColor = "#3b82f6";
    });
    card.addEventListener("mouseout", function(){
        card.style.transform = "translateY(0)";
        card.style.boxShadow = "none";
        card.style.borderColor = "#e5e5e5";
    });
});



// why lead img
const whyImage = document.querySelector(".why-img img");
whyImage.addEventListener("mouseover", function(){
    whyImage.style.transform = "scale(1.08)";
    whyImage.style.transition = "0.4s ease";
});
whyImage.addEventListener("mouseout", function(){
    whyImage.style.transform = "scale(1)";

});

// hover effect for process 
const steps = document.querySelectorAll(".step");
steps.forEach(function(step){
    step.addEventListener("mouseover", function(){
        step.style.transform = "translateY(-15px)";
        step.style.transition = "0.4s";
        step.style.backgroundColor = "#102449";
        step.style.borderRadius = "15px";
        step.style.padding = "15px";
        step.style.boxShadow = "0 10px 25px rgba(0,0,0,0.3)";
    });
    step.addEventListener("mouseout", function(){
        step.style.transform = "translateY(0)";
        step.style.backgroundColor = "transparent";
        step.style.boxShadow = "none";

    });
});

//faq
// const faqItems = document.querySelectorAll(".faq-item");
// faqItems.forEach(function(item){
//     item.addEventListener("click", function(){
//         this.style.backgroundColor = "#eef5ff";
//         this.style.borderColor = "#3b82f6";
//     });
// });

// const faqItems = document.querySelectorAll(".faq-item");

// faqItems.forEach(function(item){

//     item.addEventListener("click", function(){

//         if(this.style.backgroundColor === "rgb(238, 245, 255)"){

//             // wapas normal
//             this.style.backgroundColor = "#fff";
//             this.style.borderColor = "#ececec";

//         }
//         else{

//             // highlight
//             this.style.backgroundColor = "#eef5ff";
//             this.style.borderColor = "#3b82f6";

//         }

//     });

// });

// //faq answer

// const faqItems = document.querySelectorAll(".faq-item");

// faqItems.forEach((item) => {
//     item.addEventListener("click", () => {
//         item.classList.toggle("active");
//     });
// });
const faqItems = document.querySelectorAll(".faq-item");

faqItems.forEach(function(item){

    item.addEventListener("click", function(){

        this.classList.toggle("active");

        if(this.style.backgroundColor === "rgb(238, 245, 255)"){

            this.style.backgroundColor = "#fff";
            this.style.borderColor = "#ececec";

        }
        else{

            this.style.backgroundColor = "#eef5ff";
            this.style.borderColor = "#3b82f6";

        }

    });

});

// form fill name email
const form = document.getElementById("contactForm");

form.addEventListener("submit", async function(event) {

    event.preventDefault();

    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        company: document.getElementById("company").value,
        requirement: document.getElementById("requirement").value,
        message: document.getElementById("message").value
    };

    try {
        const response = await fetch("http://localhost:8080/api/data", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        alert(result.message);

        form.reset();

    } catch (error) {
        alert("Backend server is not running.");
        console.log(error);
    }
});