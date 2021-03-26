#  Timothy A. Meek II
#
#  File: AdventureGame.py
#  Description: This program is the structure of an adventure game that on simple commands from the user.
#               The program relies on a .txt file that can be easily customized to create a new map.
#
#  Date Created: 04/09/2020
#  Date Last Modified: 05/06/2020

class Room:

    def __init__(self,name,north,east,south,west,up,down,contents):
        # a string representing the name of the room
        self.name = name
        # a string representing the name of the room you would reach if you went north from this room
        self.north = north
        # the room you would reach if you went east from this room
        self.east = east
        # the room you would reach if you went south from this room
        self.south = south
        # the room you would reach if you went west from this room
        self.west = west
        # the room you would reach if you went up a flight of stairs from this room
        self.up = up
        # the room you would reach if you went down a flight of stairs from this room
        self.down = down
        # the contents that can be found in the rooms
        self.contents = contents

    def displayRoom(self):
        print("Room name:  " + self.name)
        if self.north != None:
            print(format("   Room to the north:","<23s") + self.north)
        if self.east != None:
            print(format("   Room to the east:","<23s") + self.east)
        if self.south != None:
            print(format("   Room to the south:","<23s") + self.south)
        if self.west != None:
            print(format("   Room to the west:","<23s") + self.west)
        if self.up != None:
            print(format("   Room above:","<23s") + self.up)
        if self.down != None:
            print(format("   Room below:","<23s") + self.down)
        print(format("   Room contents:","<22s"), self.contents)
        print()

def createRoom(roomData, contents):
    # returns a room object to the floorPlan list
    return Room(roomData[0],roomData[1],roomData[2],roomData[3],roomData[4],
                roomData[5],roomData[6],contents)

def look():
    # displays the room the user is currently in and the contents of the room
    
    print("You are currently in the", current.name + ".")
    print("Contents of the room:")
    if current.contents != []:
        for item in current.contents:
            print("   " + item)
    else:
            print("   None")
    print()

def getRoom(name):
    if name == "Living Room":
        return floorPlan[0]
    elif name == "Dining Room":
        return floorPlan[1]
    elif name == "Kitchen":
        return floorPlan[2]
    elif name == "Upper Hall":
        return floorPlan[3]
    elif name == "Bathroom":
        return floorPlan[4]
    elif name == "Small Bedroom":
        return floorPlan[5]
    elif name == "Master Bedroom":
        return floorPlan[6]

def move(direction):
    # moves the user through a map
    
    global current

    newRoom = current

    if direction == "north":
        newRoom = getRoom(current.north)
    elif direction == "east":
        newRoom = getRoom(current.east)
    elif direction == "south":
        newRoom = getRoom(current.south)
    elif direction == "west":
        newRoom = getRoom(current.west)
    elif direction == "up":
        newRoom = getRoom(current.up)
    elif direction == "down":
        newRoom = getRoom(current.down)

    if newRoom == None:
        newRoom = current
    
    if newRoom != current:  # tests to see if you can move in the direction given
        print("You are now in the " + newRoom.name + ".")
    else:
        print("You can't move in that direction.")

    print()

    return newRoom

        
def loadMap():

    global floorPlan    # stores the floor objects
    floorPlan = []
    
    mapContents = open("AdventureData.txt","r")

    room = mapContents.readline() # gets room information from txt file
    while room != "":
        roomData = []
        room = room.strip()
        room = room.split(",") # creates list from the gathered information
        for item in room:   # loop removes single quotes and converts the None string to boolean
            roomData.append(eval(item))
        contents = roomData[7:]
        roomData = roomData[:7]

        # stores the room objects into floorPlan
        floorPlan.append(createRoom(roomData,contents))

        room = mapContents.readline()

    mapContents.close()


def pickup(item):
    # places an item from the room they are in to their inventory
    if (item in current.contents) == True:
        inventory.append(item)
        print("You now have the", item)
        current.contents.remove(item)
    elif (item not in current.contents) == True:
        print("That item is not in this room.")

    print()

def drop(item):
    # drops an item from the users inventory and leaves it in the room they are currently in
    
    if (item in inventory) == True:
        inventory.remove(item)
        print("You have dropped the", item)
        current.contents.append(item)
    elif (item not in inventory) == True:
        print("You don't have that item.")

    print()

def listInventory():
    # prints the item currently in the users inventory

    print("You are currently carrying:")
    if inventory == []:
        print("   nothing.")
    elif inventory != []:
        for item in inventory:
            print("   " + item)

    print()

def main():

    global current
    global inventory

    inventory = []
    
    loadMap()
    
    #displayAllRooms()

    current = floorPlan[0]
    command = ""

    print()
    look()
    
    while command != "exit":
        command = input("Enter a command: ")

        # examine room command
        if command == "look":
            look()

        # move commands
        elif command == "north":
            current = move("north")
        elif command == "east":
            current = move("east")
        elif command == "south":
            current = move("south")
        elif command == "west":
            current = move("west")
        elif command == "up":
            current = move("up")
        elif command == "down":
            current = move("down")

        # look at inventory command
        elif command == "inventory":
            listInventory()

        # pick up item commands
        elif command == "get plate":
            pickup("plate")
        elif command == "get cup":
            pickup("cup")
        elif command == "get fork":
            pickup("fork")
        elif command == "get knife":
            pickup("knife")
        elif command == "get magazine":
            pickup("magazine")
        elif command == "get pajamas":
            pickup("pajamas")
        elif command == "get slippers":
            pickup("slippers")

        # drop item commands
        elif command == "drop plate":
            drop("plate")
        elif command == "drop cup":
            drop("cup")
        elif command == "drop fork":
            drop("fork")
        elif command == "drop knife":
            drop("knife")
        elif command == "drop magazine":
            drop("magazine")
        elif command == "drop pajamas":
            drop("pajamas")
        elif command == "drop slippers":
            drop("slippers")

        # help command
        elif command == "help":
            print(format("look:","<13s") + "display the name of the current room and its contents")
            print(format("north:","<13s") + "move north")
            print(format("east:","<13s") + "move east")
            print(format("south:","<13s") + "move south")
            print(format("west:","<13s") + "move west")
            print(format("up:","<13s") + "move up")
            print(format("down:","<13s") + "move down")
            print(format("inventory:","<13s") + "list what items you're currently carrying")
            print(format("get <item>:","<13s") + "pick up an item currently in the room")
            print(format("drop <item>:","<13s") + "drop an item you're currently carrying")
            print(format("help:","<13s") + "print this list")
            print(format("exit:","<13s") + "quit the game")
            print()


    # game over
    print("Quitting the game.")
        

main()
