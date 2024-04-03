import java.util.Calendar

def isFridayThirteenth(cal: Calendar): Boolean = {
    val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
    val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

    (dayOfWeek == Calendar.FRIDAY) && (dayOfMonth == 13)
}

while(!isFridayThirteenth(Calendar.getInstance()))
{
    println("Today isn't Friday the 13th! Lame...")
    Thread.sleep(86400000)
}
