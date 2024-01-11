document.addEventListener("DOMContentLoaded", function () {
    // Call the function to fetch courses from the Spring Boot API
    fetchMyCourses();
});

// Function to fetch courses from the Spring Boot API
function fetchMyCourses() {

    // Make a GET request to the API using jQuery
    jQuery.ajax({
        url: '/courses/getByStudent/0',
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            // debugger;
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
            <div class="c3"><a href="http://localhost:5000/getCourse/${course.id}" class="details-link">Click to see course details</a></div>
        `;
        container.appendChild(courseElement);
    });
}
