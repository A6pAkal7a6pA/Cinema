$('.slider-cover__body').slick({
    arrows: false,
    dots: false,
    slidesToShow: 7,
    slidesToScroll: 1,
    autoplay: false,
    autoplaySpeed: 2000,
    speed: 500,
    infinite: true,
    variableWidth: false,
    waitForAnimate: true,
    swipeToSlide: true
});

$(".hall__label").hover(
    function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    }
);


$('.schedule-head__item .schedule-head__link').each(function () {
    console.log(window.location.href)
    var location = window.location.href;

    var link = this.href;
    if (location == link) {
        $(this).addClass('active');
    }
});


var input = document.querySelector('.inputfile');
console.log(input);
console.log(input.nextElementSibling);
var label = input.nextElementSibling,
    labelVal = label.innerHTML;
input.addEventListener('change', function (e) {
    var fileName = "";
    if (this.files && this.files.length > 1) {
        fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
    } else {
        fileName = e.target.value.split('\\').pop();
    }
    if(fileName) {
        label.querySelector('span').innerHTML = fileName;
    } else {
        label.innerHTML = labelVal;
    }
});
