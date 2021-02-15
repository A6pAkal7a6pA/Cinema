<%@include file="header.jsp" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<section class="schedule">
    <div class="schedule__body">
        <div class="schedule__head schedule-head">
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Sunday">Sun</a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="<%=request.getContextPath()%>/schedule?day=Monday">Mon</a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Tuesday">Tue</a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Wednesday">Wed</a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Thursday">Thu</a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Friday">Fri</a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Saturday">Sat</a>
            </div>
        </div>
        <div class="schedule__content">
            <c:forEach items="${requestScope.seancesWeek}" var="seanceWeek">
                <div class="schedule__item">
                    <input type="hidden" value="<c:out value="${seanceWeek.id}"/>">
                    <span class="schedule__time"><c:out value="${seanceWeek.timeSeance}"/></span>
                    <a href="#" class="schedule__film-name"><c:out value="${seanceWeek.filmName}"/></a>
                    <a href="buy_ticket?id=<c:out value="${seanceWeek.id}"/>" class="schedule__choose">choose a
                        place</a>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<script>
    var days = [
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday'
    ];
    var a = str.split('=')[0];
    let date = new Date();
    console.log(days[date.getDay()])

    document.querySelectorAll('.schedule-head__link').forEach(function (el) {
        // console.log(window.location.pathname.indexOf(el.getAttribute('href')));
        if (window.location.pathname.indexOf(el.getAttribute('href')) > -1) {
            el.classList.add('active');
        }
    });
</script>

<%@include file="footer.jsp" %>