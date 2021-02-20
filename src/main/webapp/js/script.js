// document.querySelector('.custom-select-wrapper').addEventListener('click', function () {
//     this.querySelector('.custom-select').classList.toggle('open');
// })
// for (const option of document.querySelectorAll(".custom-option")) {
//     option.addEventListener('click', function () {
//         if (!this.classList.contains('selected')) {
//             this.parentNode.querySelector('.custom-option.selected').classList.remove('selected');
//             this.classList.add('selected');
//             this.closest('.custom-select').querySelector('.custom-select__trigger span').textContent = this.textContent;
//         }
//     })
// }
//
// window.addEventListener('click', function (e) {
//     const select = document.querySelector('.custom-select')
//     if (!select.contains(e.target)) {
//         select.classList.remove('open');
//     }
// });
//
//
// document.querySelector('.custom-select-wrapper').addEventListener('click', function () {
//     this.querySelector('.custom-select').classList.toggle('open');
// })
// for (const dropdown of document.querySelectorAll(".custom-select-wrapper")) {
//     dropdown.addEventListener('click', function () {
//         this.querySelector('.custom-select').classList.toggle('open');
//     })
// }
//
// window.addEventListener('click', function (e) {
//     const select = document.querySelector('.custom-select')
//     if (!select.contains(e.target)) {
//         select.classList.remove('open');
//     }
// });
//
// window.addEventListener('click', function (e) {
//     for (const select of document.querySelectorAll('.custom-select')) {
//         if (!select.contains(e.target)) {
//             select.classList.remove('open');
//         }
//     }
// });

$('.add-edit__title').click(function (event) {
    $(this).toggleClass("active")
});

var hint = $('#hint');
$('.hall__label[data-hint]').on({
    mouseenter: function(){
        hint.text($(this).data('hint')).show();
    },
    mouseleave: function(){
        hint.hide();
    },
    mousemove: function(e){
        hint.css({top: e.pageY, left: e.pageX});
    }
});

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
        }
        ;
    });

};

select();


$('.slider-cover__body').slick({
    arrows: false,
    dots: false,
    slidesToShow: 7,
    slidesToScroll: 1,
    autoplay: true,
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
        (this).id.innerHTML = "<b>" + $(this).id + "</b>"
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


$('.sort-by__link').each(function () {
    var location = window.location.href;

    var link = this.href;
    if (location == link) {
        $('.sort-by__link').removeClass('active');
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
    if (fileName) {
        label.querySelector('span').innerHTML = fileName;
    } else {
        label.innerHTML = labelVal;
    }
});


