var global

function setup(remoteid) {
  const url = 'https://api.immomo.com/v3/user/profile/info'
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  const http = targetClass.$new()
  const args = generateParams(remoteid)
  const response = http.doPost(url, args)
  global = deepParse(JSON.parse(response)).data
}

function generateParams(remoteid) {
  const HashMap = Java.use("java.util.HashMap")
  const root = HashMap.$new()
  root.put('myprofile_source', 'self')
  root.put('signcount', '0')
  root.put('profile_source', 'profile')
  root.put('newProfileExp', 'B')
  root.put('remoteid', remoteid)
  root.put('source_info', '{"type":"-1","extra":"com.immomo.android.module.nearbypeople.lua.presentation.fragment.NearbyPeopleLuaFragment","stack":"[{\"name\":\"NearbyPeopleLuaFragment\"},{\"data\":\"{\\\"userid\\\":\\\"1086938121\\\"}\",\"name\":\"PersonalProfileActivityK\"}]"}')
  return root
}


function deepParse(obj) {
  if (Array.isArray(obj)) {
      return obj.map(deepParse)
  } else if (typeof obj === 'object' && obj !== null) {
      Object.keys(obj).forEach(function (key) {
          var value = obj[key]
          if (typeof value === 'string') {
              try {
                  obj[key] = deepParse(JSON.parse(value))
              } catch (e) { }
          } else {
              obj[key] = deepParse(value)
          }
      })
  }
  return obj
}

rpc.exports = {
  profile: function(remoteid) {
    Java.perform(function() {
      setup(remoteid)
    })
    return global
  }
}
