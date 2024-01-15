document.addEventListener("DOMContentLoaded", function () {
    // Call the function to fetch courses from the Spring Boot API
    fetchMyCourses();
});

// Function to fetch courses from the Spring Boot API
function fetchMyCourses() {
debugger;
    // Make a GET request to the API using jQuery
    jQuery.ajax({
        url: '/courses/getByStudent',
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            ContentType: 'application/json', dataType: 'json',
        },
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            if (response && response.length > 0) {
                // Transform response data to your desired format
                var courses = response.map(function (course) {
                    return {
                        id: course.id,
                        title: course.title,
                        image: course.image
                    };
                });

                // Call a function to display the fetched courses
                displayMyCourses(courses);
            }
        },
        error: function (error) {
            console.error('Error fetching courses:', error);
        }
    });
}


function displayMyCourses(courses) {
    const container = document.getElementById('coursesContainer');


    container.innerHTML = '';
    // Iterate through the fake courses and create HTML elements
    courses.forEach(course => {
        const courseElement = document.createElement('div');
        courseElement.classList.add('course');

        courseElement.innerHTML = `
            <div class="c1"><img src="../images/${course.image}" alt="${course.title}"></div>
            <div class="c2"><h2>${course.title}</h2></div>
            <div class="c3"><a href="http://localhost:8080/getCourse/${course.id}" class="details-link">Click to see course details</a></div>
        `;
        container.appendChild(courseElement);
    });
}
