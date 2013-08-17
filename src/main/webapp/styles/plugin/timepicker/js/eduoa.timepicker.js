/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-20
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */

function initTimePicker(_box) {
    var $p = $(_box || document);

    $("input.time", $p).timepicker({ 'timeFormat': 'H:i:s' });

}