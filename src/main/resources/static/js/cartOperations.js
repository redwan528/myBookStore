$(document).ready(function() {
    $('.add-to-cart-btn').click(function(e) {
        e.preventDefault();  // Prevent the default click behavior
        var bookId = $(this).data('bookId');
        $.ajax({
            type: 'POST',
            url: '/cart/add',
            data: { bookId: bookId },
            success: function(response) {
                alert("BOOK ADDED TO CART");  // Display a pop-up message with the server's response
            }
        });
    });
});
