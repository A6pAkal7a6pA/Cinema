let select = function () {
    let selectHeader = document.querySelectorAll('.lang-select__header');
    let selectItem = document.querySelectorAll('.lang-select__item');
    let menuBtn = $('.lang-select');
    selectHeader.forEach(item => {
        item.addEventListener('click', selectToggle)
    });

    selectItem.forEach(item => {
        item.addEventListener('click', selectChoose)
    });

    function selectToggle() {
        this.parentElement.classList.toggle('is-active');
    }

    function selectChoose() {

        let text = this.innerHTML,
            select = this.closest('.lang-select'),
            currentText = select.querySelector('.lang-select__current');
        currentText.innerHTML = text;
        select.classList.remove('is-active');
    }
    $(document).click(function (e) {
        if (!menuBtn.is(e.target) && menuBtn.has(e.target).length === 0) {
            menuBtn.removeClass('is-active');
        };
    });

};

select();


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
    var location = window.location.href;

    var link = this.href;
    if (location == link) {
        $(this).addClass('active');
    }
});

$('.menu__list-link').each(function () {
    var location = window.location.href;

    var link = this.href;
    if (location == link) {
        $(this).addClass('active');
    }
});


var input = document.querySelector('.inputfile');
var label = input.nextElementSibling;
var labelVal = label.innerHTML;
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


