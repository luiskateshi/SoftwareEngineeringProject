document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('newFeedback').addEventListener('click', function () {
        document.getElementById('feedbackForm').style.display = 'block';
        document.getElementById('newFeedback').style.display = 'none';
    });

    document.getElementById('feedbackForm').style.display = 'none';

    const currentUrl = window.location.href;
    const urlParts = currentUrl.split('/');
    const id = urlParts[urlParts.length - 1];
    fetchFeedbacks(id);
});


function fetchFeedbacks(courseId){
    jQuery.ajax({
        url: `/feedbacks/getByCourse/${courseId}`,
        type: 'GET',
        dataType: 'json',
        success: function (feedbacks) {
            displayFeedbackCards(feedbacks);
        },
        error: function (error) {
            console.error('Error fetching course details:', error);
            document.getElementById('feedbacks_msg').innerHTML = 'No feedbacks yet!';

        }
    });
}


function displayFeedbackCards(feedbacks) {
    debugger;
    const feedbacksContainer = document.getElementById('feedbacksContainer');
    feedbacksContainer.innerHTML = '';

    // Iterate through the feedbacks and create HTML elements
    feedbacks.forEach(feedback => {
        const feedbackCard = createFeedbackCardElement(feedback);
        feedbacksContainer.appendChild(feedbackCard);
    });
}

function createFeedbackCardElement(feedback) {
    debugger;
    const card = document.createElement('div');
    card.classList.add('card', 'mb-3');

    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    const title = document.createElement('h5');
    title.classList.add('card-title');
    title.textContent = feedback.studentName;

    const text = document.createElement('p');
    text.classList.add('card-text');
    text.textContent = feedback.message;

    const ratingDiv = document.createElement('div');
    ratingDiv.classList.add('rating');

    // Adding stars based on the rating
    for (let i = 0; i < feedback.rating; i++) {
        const star = document.createElement('i');
        star.classList.add('fas', 'fa-star');
        ratingDiv.appendChild(star);
    }

    cardBody.appendChild(title);
    cardBody.appendChild(text);
    cardBody.appendChild(ratingDiv);

    card.appendChild(cardBody);

    return card;
}
