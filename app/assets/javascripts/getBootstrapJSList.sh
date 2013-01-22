for i in `ls bootstrap/*`; do echo '<script src="@routes.Assets.at("'$i'")"></script>' | sed 's/\.js/.min.js/g'; done
