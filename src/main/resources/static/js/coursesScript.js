document.addEventListener("DOMContentLoaded", function () {
    // Fake data for testing
    const fakeCourses = [
        {
            title: "Object Oriented Programming",
            image: "../images/pol.png",
            detailsLink: "#"
        },
        {
            title: "Automation",
            image: "../images/automation.png",
            detailsLink: "#"
        },
        {
            title: "Object Oriented Programming",
            image: "../images/pol.png",
            detailsLink: "#"
        },
        {
            title: "Automation",
            image: "../images/automation.png",
            detailsLink: "#"
        },
        {
            title: "Object Oriented Programming",
            image: "../images/pol.png",
            detailsLink: "#"
        },
        {
            title: "Automation",
            image: "../images/automation.png",
            detailsLink: "#"
        }
        // Add more fake courses as needed
    ];

    // Call a function to display the fake courses
    displayCourses(fakeCourses);
});

// Function to display courses dynamically
function displayCourses(courses) {
    const container = document.getElementById('coursesContainer');

    // Iterate through the fake courses and create HTML elements
    courses.forEach(course => {
        const courseElement = document.createElement('div');
        courseElement.classList.add('course');

        courseElement.innerHTML = `
            <div class="c1"><img src="${course.image}" alt="${course.title}"></div>
            <div class="c2"><h2>${course.title}</h2></div>
            <div class="c3"><a href="${course.detailsLink}" class="details-link">Click to see course details</a></div>
        `;

        container.appendChild(courseElement);
    });
}
