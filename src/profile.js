var v_profile

function setup(remoteid) {
  const url = 'https://api.immomo.com/v3/user/profile/info'
  const targetClass = Java.use('com.immomo.momo.protocol.http.a.a')
  const http = targetClass.$new()
  const args = generateParams(remoteid)
  const response = http.doPost(url, args)
  v_profile = v_profile_deep(JSON.parse(response)).data
}

function generateParams(remoteid) {
  const userId = '1096985138'
  const LinkedHashMap = Java.use("java.util.LinkedHashMap")
  const root = LinkedHashMap.$new()
  root.put('myprofile_source', 'self')
  root.put('signcount', '0')
  root.put('profile_source', 'profile')
  root.put('newProfileExp', 'B')
  root.put('remoteid', remoteid)
  root.put('source_info', '{"type":"-1","extra":"com.immomo.android.module.nearbypeople.lua.presentation.fragment.NearbyPeopleLuaFragment","stack":"[{\"name\":\"NearbyPeopleLuaFragment\"},{\"data\":\"{\\\"userid\\\":\\\"' + userId + '\\\"}\",\"name\":\"PersonalProfileActivityK\"}]"}')
  return root
}


function v_profile_deep(obj) {
  if (Array.isArray(obj)) {
      return obj.map(v_profile_deep)
  } else if (typeof obj === 'object' && obj !== null) {
      Object.keys(obj).forEach(function (key) {
          var value = obj[key]
          if (typeof value === 'string') {
              try {
                  obj[key] = v_profile_deep(JSON.parse(value))
              } catch (e) { }
          } else {
              obj[key] = v_profile_deep(value)
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
    return v_profile
  }
}
