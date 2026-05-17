// Máscara para celular: (11) 99999-9999
$("#inputPhone").mask("(00) 00000-0000");


function convertPhoneToString(phoneFormatted) {
    return phoneFormatted.replace(/\D/g, '');
}

function formatPhoneToMask(phoneDigits) {
    return `(${phoneDigits.slice(0, 2)}) ${phoneDigits.slice(2, 7)}-${phoneDigits.slice(7)}`;
}

//Data
var students = [];
var courses = [];

//OnLoad
loadCourses();
loadStudents();

//Load all Courses
function loadCourses() {
    $.ajax({
        url: "http://localhost:8080/courses",
        type: "GET",
        async: false,
        success: (response) => {
            courses = response;
            var selectCourse = document.getElementById("selectCourse");
            if (!selectCourse) {
                return;
            }

            selectCourse.innerHTML = '<option value="" selected>Escolha:</option>';
            for (var course of courses) {
                selectCourse.innerHTML += `<option value="${course.id}">${course.name}</option>`;
            }
        }
    })
}

//Load all students
function loadStudents() {

    $.getJSON("http://localhost:8080/students", (response) => {
        students = response
        for (let stud of students) {
            addNewRow(stud);
        }
    });
}

//save a data students
function save() {
    var stud = {
        id: students.length + 1,
        name: document.getElementById("inputName").value,
        email: document.getElementById("inputEmail").value,
        phone: convertPhoneToString(document.getElementById("inputPhone").value),
        idCurso: Number(document.getElementById("selectCourse").value),
        period: Number(document.querySelector('input[name="turno"]:checked')?.value)
    };


    $.ajax({
        url: "http://localhost:8080/students",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(stud),
        success: (student) => {
            addNewRow(student);
            students.push(student);
            document.getElementById("formStudent").reset();
        },
        error: () => {
            alert("Nao foi possivel salvar o aluno. Verifique se o backend esta rodando.");
        }
    });
}


//Add new Row
function addNewRow(stud) {
    var tableBody = document.querySelector("#studentsTable tbody");

    var newRow = tableBody.insertRow();

    //Insert student id
    var idNode = document.createTextNode(stud.id);
    newRow.insertCell().appendChild(idNode);

    //Insert student name
    var nameNode = document.createTextNode(stud.name);
    newRow.insertCell().appendChild(nameNode);

    //Insert student email
    var emailNode = document.createTextNode(stud.email);
    newRow.insertCell().appendChild(emailNode);

    //Insert student phone
    var phoneNode = document.createTextNode(formatPhoneToMask(stud.phone));
    newRow.insertCell().appendChild(phoneNode);

    //Insert course name
    var currentCourse = courses.find((c) => c.id === stud.idCurso);
    var courseNode = document.createTextNode(currentCourse ? currentCourse.name : "-");
    newRow.insertCell().appendChild(courseNode);

    var periodList = "";
    var period = Number(stud.period);
    if (period === 1) {
        periodList = "<span class='badge' style='background-color: #38c4da; color: black;'>Manhã</span>";
    } else if (period === 2) {
        periodList = "<span class='badge' style='background-color: #e7ce3c; color: black;'>Tarde</span>";
    } else if (period === 3) {
        periodList = "<span class='badge' style='background-color: #343a40; color: white;'>Noite</span>";
    }

    var cell = newRow.insertCell();
    cell.innerHTML = periodList;

}












