
document.addEventListener("DOMContentLoaded", function () {
    // Call the function to fetch courses from the Spring Boot API
    const id = getCourseId();
    //on click enroll button
    document.getElementById('enrollButton').addEventListener('click', function () {
        enrollStudent(id);
    });

    //on click unenroll button
    document.getElementById('unregButton').addEventListener('click', function () {
        unregisterStudent(id);
    });
    fetchCourseDetails(id);
    fetchEnrollmentStatus(id);
});

//get course id function
function getCourseId(){
    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    return urlParts[urlParts.length - 1];
}

function fetchCourseDetails(courseId){
// AJAX request to fetch course details
    jQuery.ajax({
        url: `/courses/get/${courseId}`, // Endpoint to get course details by ID
        type: 'GET',
        dataType: 'json',
        success: function (course) {
            // Display course details on the page
            debugger;
            displayCourseDetails(course);
        },
        error: function (error) {
            console.error('Error fetching course details:', error);
        }
    });

}

function fetchEnrollmentStatus(courseId){
// AJAX request to fetch course details
    jQuery.ajax({
        url: `/studentEnrollments/isStudentEnrolledInCourse/${courseId}`,
        type: 'GET',
        dataType: 'json',
        success: function (isEnrolled) {
            if(isEnrolled)
                displayUnregButton();
            else
                displayEnrollButton();
        },
        error: function (error) {
            console.error('Error fetching enrollment status:', error);
        }
    });

}

function enrollStudent(courseId){
// AJAX request to fetch course details
    jQuery.ajax({
        url: `/studentEnrollments/saveStudentEnrollment/${courseId}`,
        type: 'GET',
        dataType: 'json',
        success: function (response) {

            alert("You have been enrolled in this course!")
            displayUnregButton();
        },
        error: function (error) {
            console.error('Error enrolling student:', error);
        }
    });

}

function unregisterStudent(courseId){
// AJAX request to fetch course details
    jQuery.ajax({
        url: `/studentEnrollments/deleteStudentEnrollment/${courseId}`,
        type: 'DELETE',
        dataType: 'json',
        success: function (flag) {
            if(flag)
                displayEnrollButton();

            alert("You have been unregistered from this course!")
        },
        error: function (error) {
            console.error('Error unregistering student:', error);
        }
    });

}

//method to display enroll button if not enrolled
function displayEnrollButton(){
    document.getElementById('enrollButton').style.display = 'block';
    document.getElementById('unregButton').style.display = 'none';
}

//method to display unenroll button if enrolled
function displayUnregButton(){
    document.getElementById('enrollButton').style.display = 'none';
    document.getElementById('unregButton').style.display = 'block';
}

function displayCourseDetails(course) {
    // Set the page title
    document.title = course.title;

    // Set the course title
    document.getElementById('title').innerText = course.title;

    // Set the course image
    document.getElementById('image').src = "../images/" + course.image;

    // Set the course description
    document.getElementById('description').innerText = course.description;

    document.getElementById('lecturer').innerText = course.description;

    // Set the course start date
    document.getElementById('start-day').innerText = course.startDate;

    document.getElementById('current-number').innerText = course.currentNumberOfStudents;

    document.getElementById('max-number').innerText = course.maxNumberOfStudents;

    // Set the course
}