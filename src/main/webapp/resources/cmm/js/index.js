/**
 * 
 */	
var cmm = cmm || {}
cmm = (() => {
	const init = ctx => {
		alert(`${ctx}`)
    $('.move-home').click(e => {location.href = '/'})
	$('#manager_register').click(e => { location.href = `${ctx}/transfer/sym/mgr/register`})
	$('#teacher_register').click(e => { location.href = `${ctx}/transfer/sym/tea/register`})	
	}
	return {init}
})()