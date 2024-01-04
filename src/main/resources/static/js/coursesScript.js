document.addEventListener("DOMContentLoaded", function () {
    // Call the function to fetch courses from the Spring Boot API
    fetchCourses();
});

$(document).ready(function () {
    $("#searchForm").submit(function (event) {
        event.preventDefault();
        searchCourses();
    });
});


function searchCourses() {

    //let search = {}
    //search["search"] = $("#searchInput").val();
    var endpoint = '/courses/search?search=' + encodeURIComponent($("#searchInput").val());

    // Make a GET request to the API using jQuery
    jQuery.ajax({
        url: endpoint,
        type: 'GET',
        //data: JSON.stringify(search),
        dataType: 'json',
        success: function (response) {
            if (response && response.length > 0) {
                var courses = response.map(function (course) {
                    return {
                        id: course.id,
                        title: course.title,
                        detailsLink: "#",
                        image: course.image
                    };
                });
                displayCourses(courses);
            }
        },
        error: function (error) {
            debugger;
            document.getElementById('coursesContainer').innerHTML = '';
            console.error('Error fetching courses:', error);
        }
    });
}
// Function to fetch courses from the Spring Boot API
function fetchCourses() {

    // Make a GET request to the API using jQuery
    jQuery.ajax({
        url: '/courses/getAll',
        type: 'GET',
        dataType: 'json',
        success: function (response) {
        debugger;
            // Once data is fetched successfully, display the courses
            if (response && response.length > 0) {
                // Transform response data to your desired format
                var courses = response.map(function (course) {
                    return {
                        id: course.id,
                        title: course.title,
                        detailsLink: "#",
                        image: course.image
                        // Add other necessary properties based on your data
                        // className, icon, etc.
                    };
                });

                // Call a function to display the fetched courses
                displayCourses(courses);
            }
        },
        error: function (error) {
           console.error('Error fetching courses:', error);
        }
    });
}


function displayCourses(courses) {
    const container = document.getElementById('coursesContainer');


    container.innerHTML = '';
    // Iterate through the fake courses and create HTML elements
    courses.forEach(course => {
        const courseElement = document.createElement('div');
        courseElement.classList.add('course');

        courseElement.innerHTML = `
            <div class="c1"><img src="../images/${course.image}" alt="${course.title}"></div>
            <div class="c2"><h2>${course.title}</h2></div>
            <div class="c3"><a href="http://localhost:5000/getCourse/${course.id}" class="details-link">Click to see course details</a></div>
        `;
        container.appendChild(courseElement);
    });
}
