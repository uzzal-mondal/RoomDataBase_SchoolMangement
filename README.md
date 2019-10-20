# RoomDataBaseSchoolMangement_ThankFullToShaplaApu

## 
      ## Fragment Use to BackButton.. ##

# 1. create a custom Toolbar under a layout res.
# 2. then include to activity main and declare to toolbar_id.
# 3. Variable declare to toolbar.
# 4. then toolbar id - findViewById.
# 5. toolbar set text color.
# 6. actionbar to set toolbar.
# 7. back button create a enabled 
    ## getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ## getSupportActionBar().setDisplayShowHomeEnabled(true);
# 8. override to onOptions itemSelected item.
# 9. check to android.R.id.home - default back button call.
# 10. Declare to main Fragment type preFragment, currentFragment.
    with declare to bollean flag.
# 11. Create a backPressed method(); 
    want to 2 parameter are Fragment type,
    prevFragment, currentFragment.
# 12. Check to bollean value within fragment transiction.
# 13. then your logic, preFragment to your Fragment.
    and currentFragmet to other Fragment.
# 14. then Fragment transiction start..
# 15. Lastly activity to finish.
