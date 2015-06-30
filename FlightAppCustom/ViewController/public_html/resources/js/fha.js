(function () {
 
 doAlert = function () {
        var args = arguments;

        var str = "doAlert, argCount:" + args.length + ", arguments:";

        for (x = 0;x < args.length;x++) {
            if (x > 0) {
                str += ", ";
            }
            str += arguments[x];
        }

        alert(str);
    };

  //register  
  popupUtilsShowPopup = function() {
        //the argument is required and cannot be missing
        if (arguments.length > 0) {
            var popupOpener = document.getElementById(arguments[0]);
            if (popupOpener != null && popupOpener != undefined) {
                adf.mf.api.amx.triggerBubbleEventListener(popupOpener,"tap");
            }
            else {
                adf.mf.log.Application.logp(adf.mf.log.level.WARNING,
                  "PopupUtils", "showPopup", "hidden button to launch popup not found");
            }
        }
        else {
            adf.mf.log.Application.logp(adf.mf.log.level.WARNING,
              "PopupUtils", "showPopup", "Missing input argument");
        }
    }

    popupUtilsHidePopup = function() {
        //the argument is required and cannot be missing
        if (arguments.length > 0) {
            var popupCloser = document.getElementById(arguments[0]);

            if (popupCloser != null && popupCloser != undefined) {
                adf.mf.api.amx.triggerBubbleEventListener(popupCloser,"tap");
            }
            else {
                adf.mf.log.Application.logp(adf.mf.log.level.WARNING,
                "PopupUtils","showPopup", "hidden button to close popup not found");
            }
        }
        else {
            adf.mf.log.Application.logp(adf.mf.log.level.WARNING,
             "PopupUtils", "hidePopup", "Missing input argument");
        }
    }

})(); 