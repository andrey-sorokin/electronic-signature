###############################################################################
#
# Function    : initialise_environment
#
# Description : Initialize the parameters necessary to run application on
#               various flavors of Unix
#
# Parameters  : None
#
###############################################################################
function initialise_environment
{
    systemType=$(uname -s)
    hostname=$(uname -n)

    PID_RECORDER=pid_recorder.sh
    PROG_LANG=awk

    case ${systemType} in
        SunOS)
            ECHO="echo "
            PROG_LANG=nawk
            ;;
	AIX)
	    bold=""
	    norm=""
	    ECHO="echo "
	    ;;
        NONSTOP_KERNEL)
	    bold="\033[1;38m"
	    norm="\033[m\017"
	    ECHO="echo "
            USER=`whoami`
	    ;;
	*)
	    bold="\033[1;38m"
	    norm="\033[m\017"
	    ECHO="echo -e "
	    ;;
    esac

    if [ -x /usr/ucb/ps ] ; then
      PS="/usr/ucb/ps -auxwww"
    else
      PS="ps -ef"
    fi
	
if [ -z $JAVA_HOME ]
then
	JAVA_HOME=/usr/jdk/latest
fi
}

###############################################################################
#
# Function    : check_environment
#
# Description : Check that JAVA_HOME is set.
#
# Parameters  : none
#
###############################################################################
function check_environment
{
    if [[ -z "$JAVA_HOME" ]] ; then
	${ECHO} "${bold}"
    ${ECHO} " ***************************************************************"
	${ECHO} " FATAL ERROR: The JAVA_HOME environment variable is not set"
	${ECHO} " Please set this to the appropriate value for your JDK or JRE"
	${ECHO} " installation"
	${ECHO} " ***************************************************************"
	${ECHO} "${norm}"
	exit 1
    fi
}

###############################################################################
#
# Function    : add_jar_to_classpath
#
# Description : This function appends new jars to the java CLASSPATH
#
# Parameters  : none
#
###############################################################################
function add_jar_to_classpath
{
    JAR=$1

    if [[ -r $JAR ]] ; then
        CLASSPATH=$CLASSPATH:$JAR
    fi
}

###############################################################################
#
# Function    : build_launch_codes
#
# Description : This function creates the LAUNCH instruction for running application.
#              
# Parameters  : none
#
###############################################################################
function build_launch_codes
{
    JAVA_HOME=$JAVA_HOME

    echo "JAVA_HOME is " $JAVA_HOME
    # Define a default CLASSPATH
    cd ..
    WORKDIR=`pwd`
    CLASSPATH=$WORKDIR
    echo "CLASSPATH is " $CLASSPATH
    cd ./lib
    for filename in *.jar
    do
	    CLASSPATH=$CLASSPATH:$WORKDIR/lib/$filename
    done

    echo "CLASSPATH is " $CLASSPATH

    # Append the complete CLASSPATH to launche codes
    LAUNCH="${JAVA_HOME}/bin/java -Duser.language=en -Duser.region=us -cp ${CLASSPATH}"
    
    echo "LAUNCH is " $LAUNCH
}

###############################################################################
#
# Main program
#
###############################################################################
#echo "Application is running..."

# Initialize application host environment
#initialise_environment;

# Check the environment properties have been set properly
#check_environment;

build_launch_codes;

echo "LAUNCH is "$LAUNCH
nohup ${LAUNCH}  ru.rstyle.si.MyServiceDemo  &
