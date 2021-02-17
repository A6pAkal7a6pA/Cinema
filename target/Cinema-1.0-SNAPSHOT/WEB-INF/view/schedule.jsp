<%@include file="header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<section class="schedule">
    <div class="schedule__body">
        <div class="schedule__head schedule-head">
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Sunday">
                    <fmt:message key="schedule.table.sunday" bundle="${bundle}" var="schsun"/>${schsun}
                </a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="<%=request.getContextPath()%>/schedule?day=Monday">
                    <fmt:message key="schedule.table.monday" bundle="${bundle}" var="schmon"/>${schmon}
                </a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Tuesday">
                    <fmt:message key="schedule.table.tuesday" bundle="${bundle}" var="schthe"/>${schthe}
                </a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Wednesday">
                    <fmt:message key="schedule.table.wednsday" bundle="${bundle}" var="schwed"/>${schwed}
                </a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Thursday">
                    <fmt:message key="schedule.table.thursday" bundle="${bundle}" var="schthu"/>${schthu}
                </a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Friday">
                    <fmt:message key="schedule.table.friday" bundle="${bundle}" var="schfri"/>${schfri}
                </a>
            </div>
            <div class="schedule-head__item">
                <a class="schedule-head__link" href="?day=Saturday">
                    <fmt:message key="schedule.table.saturday" bundle="${bundle}" var="schsat"/>${schsat}
                </a>
            </div>
        </div>
        <div class="schedule__content">
            <c:forEach items="${requestScope.seancesWeek}" var="seanceWeek">
                <div class="schedule__item">
                    <input type="hidden" value="<c:out value="${seanceWeek.id}"/>">
                    <span class="schedule__time"><c:out value="${seanceWeek.timeSeance}"/></span>
                    <a href="buy_ticket?id=<c:out value="${seanceWeek.id}"/>" class="schedule__film-name"><c:out value="${seanceWeek.filmName}"/></a>
                    <a href="buy_ticket?id=<c:out value="${seanceWeek.id}"/>" class="schedule__choose">
                        <fmt:message key="schedule.table.choose" bundle="${bundle}" var="schchoose"/>${schchoose}
                    </a>
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
        if (window.location.pathname.indexOf(el.getAttribute('href')) > -1) {
            el.classList.add('active');
        }
    });
</script>

<%@include file="footer.jsp" %>