
document.addEventListener("DOMContentLoaded", function () {
    // Call the function to fetch courses from the Spring Boot API
    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const id = urlParts[urlParts.length - 1];
    fetchCourseDetails(id);
});

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